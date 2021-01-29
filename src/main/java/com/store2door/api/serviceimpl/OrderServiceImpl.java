package com.store2door.api.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store2door.api.dto.DeliveryChargesDTO;
import com.store2door.api.dto.MyOrdersDataDTO;
import com.store2door.api.dto.UserAddressDTO;
import com.store2door.api.dto.UserCartDTO;
import com.store2door.api.dto.ZipcodeDTO;
import com.store2door.api.model.CategoryItems;
import com.store2door.api.model.DeliveryCharges;
import com.store2door.api.model.Store2doorStores;
import com.store2door.api.model.User;
import com.store2door.api.model.UserAddress;
import com.store2door.api.model.UserCart;
import com.store2door.api.model.UserOrderItems;
import com.store2door.api.model.UserOrderItemsHistory;
import com.store2door.api.model.UserOrders;
import com.store2door.api.model.UserOrdersHistory;
import com.store2door.api.model.Zipcode;
import com.store2door.api.repository.DeliveryChargesRepository;
import com.store2door.api.repository.UserAddressRepository;
import com.store2door.api.repository.UserFlowRepository;
import com.store2door.api.repository.UserOrderHistoryRepository;
import com.store2door.api.repository.UserOrderItemsHistoryRepository;
import com.store2door.api.repository.UserOrderItemsRepository;
import com.store2door.api.repository.UserOrdersRepository;
import com.store2door.api.repository.ZipcodeRepository;
import com.store2door.api.security.UserPrincipal;
import com.store2door.api.service.MailerService;
import com.store2door.api.service.OrderService;
import com.store2door.api.utils.Constants;
import com.store2door.api.utils.JString;
import com.store2door.api.utils.Store2doorUtils;

@SuppressWarnings("static-access")
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private UserAddressRepository userAddress;
	@Autowired
	private UserOrdersRepository userOrders;
	@Autowired
	private UserFlowRepository cartDetails;
	@Autowired
	private UserOrderItemsRepository userOrderItems;
	@Autowired
	private UserOrderHistoryRepository userOrdersHistory;
	@Autowired
	private  Store2doorUtils utils;
	@Autowired
	private UserOrderItemsHistoryRepository userOrderItemHistory;
	@Autowired
	MailerService mailerService;
	@Value("${app.env}")
	private String env;
	@Autowired
	ZipcodeRepository zipcodeRepository;
	@Autowired
	DeliveryChargesRepository deliveryChargesRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserAddressDTO addAddress(UserAddressDTO dto, UserPrincipal currentUser) throws Exception {
		UserAddressDTO addressDto = null;
		try {
			UserAddress address = new UserAddress(new User(currentUser.getId()), dto.getName(),
					dto.getStreetAddress(), dto.getCity(), dto.getState(), dto.getContNo(), dto.getPin(),
					dto.getAddress(), new Date(),dto.getLandMark());
			userAddress.save(address);
			addressDto = new UserAddressDTO(address.getId(), address.getName(), address.getAddress(),
						address.getStreetAddress(), address.getPinCode(), address.getState(), address.getContactNo(),
						address.getCity(),address.getLandMark());
			
		}catch (Exception exception) {
			throw exception;
		}
		
		return addressDto;
	}
	@Override
	public List<UserAddressDTO> getCurrentUserAddress(UserPrincipal currentUser) throws Exception {
		List<UserAddressDTO> list=new ArrayList<UserAddressDTO>();
		try {
			for(UserAddress info:userAddress.findByUserIdOrderByCreatedTimeDesc(currentUser.getId())) {
				list.add(new UserAddressDTO(info.getId(), info.getName(), info.getAddress(),
							info.getStreetAddress(), info.getPinCode(), info.getState(), info.getContactNo(),
							info.getCity(),info.getLandMark()));
			}
			
			/*return userAddress.findByUserId(currentUser.getId()).stream()
					.map(info -> new UserAddressDTO(info.getId(), info.getName(), info.getAddress(),
							info.getStreetAddress(), info.getPinCode(), info.getState(), info.getContactNo(),
							info.getCity()))
					.collect(Collectors.toList());*/
		} catch (Exception exception) {
			throw exception;
		}
		return list;
	}
	@Override
	public boolean addOrderData(MyOrdersDataDTO dto, UserPrincipal currentUser) throws Exception {
		boolean result;
		try {
			if (!dto.getOrderItemsData().isEmpty() && dto.getOrderItemsData().iterator().next().getStoreId() != 0) {
				// set the item values
				UserOrders ordersPlace = new UserOrders(
						new Store2doorStores(dto.getOrderItemsData().iterator().next().getStoreId()),
						new User(currentUser.getId()), new Date(), dto.getPaymentType(), dto.getTotalPrice());
				Set<UserOrderItems> userOrderItemses = dto.getOrderItemsData().stream()
						.map(data -> new UserOrderItems(new CategoryItems(data.getItemId()), ordersPlace,
								data.getItemQuantity(), data.getTotalPrice() * data.getItemQuantity(),
								dto.getOrderStatus(), new Date(), new Date(), new UserAddress(dto.getOrderAddressId())))
						.collect(Collectors.toSet());
				// place the order data
				ordersPlace.setUserOrderItemses(userOrderItemses);
				userOrders.save(ordersPlace);

				List<UserCart> userCartsList = dto.getOrderItemsData().stream().map(cart -> new UserCart(cart.getId()))
						.collect(Collectors.toList());
				cartDetails.deleteAll(userCartsList);
				result = Constants.SUCCESS.isStatus();
			} else {
				result = !Constants.SUCCESS.isStatus();
			}
		} catch (Exception exception) {
			throw exception;
		}
		return result;
	}
	@Override
	public List<MyOrdersDataDTO> loadOrdersForUser(UserPrincipal currentUser) throws Exception {
		try {
			return populateOrdersData(userOrders.findByUserId(currentUser.getId()));
		} catch (Exception exception) {
			throw exception;
		}
	}
	@Override
	public List<MyOrdersDataDTO> loadAllOrders(UserPrincipal currentUser) throws Exception {
		try {
			return populateOrdersData(userOrders.findAll());
		}catch (Exception exception) {
			throw exception;
		}
	}
	@Override
	public List<MyOrdersDataDTO> orderStatusChanged(UserCartDTO dto, UserPrincipal currentUser) throws Exception {
		List<MyOrdersDataDTO> loadAllOrders = new ArrayList<MyOrdersDataDTO>();
		try {
			Optional<UserOrderItems> userOrderItemsData = userOrderItems.findById(dto.getId());
			if (userOrderItemsData.isPresent()) {
				UserOrderItems info = userOrderItemsData.get();
				if (!Constants.ITEMDELIVERED.getValue().equalsIgnoreCase(dto.getItemStatus())) {
					info.setItemStatus(dto.getItemStatus());
					info.setLastUpdatedTime(new Date());
					userOrderItems.save(info);
					CompletableFuture.runAsync(()->{
						User userInfo = info.getUserOrders().getUser();
						if (!JString.isEmpty(userInfo.getDeviceId()))
							try {
								utils.sendPushNotification("Your ordered item is "+dto.getItemStatus(),"Your Order  "+info.getCategoryItems().getItemName()+" is"+dto.getItemStatus()+"", userInfo.getDeviceId());
							} catch (Exception e) {
								 mailerService.sendEmailOnException(env, "Exception is Form Mr Green Backend API", e);
							}
					});
				} else {
						UserOrders orderInfo = info.getUserOrders();
						saveOrdersHistoryData(orderInfo, info, Constants.ITEMDELIVERED.getValue());
						List<UserOrderItems> orderItemList = userOrderItems
								.findByUserOrders(new UserOrders(info.getUserOrders().getId()));
						userOrderItems.deleteById(info.getId());
						if (orderItemList.size() == 1)
							userOrders.deleteById(info.getUserOrders().getId());
				}
				loadAllOrders = loadAllOrders(currentUser);
			}
		}catch (Exception exception) {
			throw exception;
		}
		return loadAllOrders;
	}
	
	@Override
	public List<MyOrdersDataDTO> loadOrderedHistoryForUser(UserPrincipal currentUser) throws Exception {
		try {
			List<MyOrdersDataDTO> myOrdersDataDTOs = new ArrayList<MyOrdersDataDTO>();
			userOrdersHistory.findByUserIdOrderByOrderTimeDesc(currentUser.getId()).forEach(orderData -> {
				MyOrdersDataDTO dataDTO = new MyOrdersDataDTO(orderData.getId(), orderData.getstore2doorStores().getId(),
						String.valueOf(orderData.getPaymentType()), (long)orderData.getTotalPrice(), 0l,orderData.getOrderTime());
				dataDTO.setOrderItemsData(orderData.getUserOrderItemsHistories().stream()
						.map(itemData -> new UserCartDTO(itemData.getId(), itemData.getCategoryItems().getId(),
								itemData.getCategoryItems().getItemName(), dataDTO.getOrderForm(),
								orderData.getstore2doorStores().getStoreName(), itemData.getItemQuantity(),
								itemData.getItemPrice(),
								itemData.getCategoryItems().getStoreCategoryItemImageses().iterator().hasNext()
										? itemData.getCategoryItems().getStoreCategoryItemImageses().iterator().next()
												.getItemImage(): "",itemData.getCreatedTime(),itemData.getLastUpdatedTime(),itemData.getItemStatus(),new UserAddressDTO(),itemData.getRating(),itemData.getComments(),""))
						.collect(Collectors.toList()));
				dataDTO.setCancelledItems(dataDTO.getOrderItemsData().stream().filter(data -> Constants.ITEMCANCELLED.getValue().equals(data.getItemStatus())).collect(Collectors.toList()));
				dataDTO.setDeliveredItems(dataDTO.getOrderItemsData().stream().filter(data -> Constants.ITEMDELIVERED.getValue().equals(data.getItemStatus())).collect(Collectors.toList()));
				myOrdersDataDTOs.add(dataDTO);
			});
			return myOrdersDataDTOs;
		} catch (Exception exception) {
			throw exception;
		}
	}
	private List<MyOrdersDataDTO> populateOrdersData(List<UserOrders> ordersList){
		try {
			List<MyOrdersDataDTO> myOrdersDataDTOs = new ArrayList<MyOrdersDataDTO>();
			ordersList.forEach(orderData ->{
				MyOrdersDataDTO dataDTO = new MyOrdersDataDTO(orderData.getId(), orderData.getstore2doorStores().getId(),
						String.valueOf(orderData.getPaymentType()), (long)orderData.getTotalPrice(), 0l,orderData.getOrderTime());
				dataDTO.setOrderItemsData(orderData.getUserOrderItemses().stream()
						.map(itemData -> new UserCartDTO(itemData.getId(), itemData.getCategoryItems().getId(),
								itemData.getCategoryItems().getItemName(), dataDTO.getOrderForm(),
								orderData.getstore2doorStores().getStoreName(), itemData.getItemQuantity(),
								itemData.getItemPrice(),
								itemData.getCategoryItems().getStoreCategoryItemImageses().iterator().hasNext()
										? itemData.getCategoryItems().getStoreCategoryItemImageses().iterator().next()																																							
												.getItemImage(): "",itemData.getCreatedTime(),itemData.getLastUpdatedTime(),itemData.getItemStatus(),new UserAddressDTO(itemData.getUserAddress().getId(), itemData.getUserAddress().getName(), itemData.getUserAddress().getAddress(), itemData.getUserAddress().getStreetAddress(), itemData.getUserAddress().getPinCode(), itemData.getUserAddress().getState(),
														itemData.getUserAddress().getContactNo(), itemData.getUserAddress().getCity(),itemData.getUserAddress().getLandMark()),0,"",itemData.getCategoryItems().getUnitofMesure()))
						.collect(Collectors.toList()));
				dataDTO.setOrderPlacedItems(dataDTO.getOrderItemsData().stream().filter(data -> Constants.ORDERPLACED.getValue().equals(data.getItemStatus())).collect(Collectors.toList()));
				dataDTO.setShippedItmes(dataDTO.getOrderItemsData().stream().filter(data -> Constants.ITEMSHIPPED.getValue().equals(data.getItemStatus())).collect(Collectors.toList()));
				dataDTO.setOutForDelieryItems(dataDTO.getOrderItemsData().stream().filter(data -> Constants.OUTFORDELIDERY.getValue().equals(data.getItemStatus())).collect(Collectors.toList()));
				
				myOrdersDataDTOs.add(dataDTO);
			});
			return myOrdersDataDTOs;
		}catch(Exception exception) {
			throw exception;
		}
	}
	@Override
	public boolean saveOrdersHistoryData(UserOrders info, UserOrderItems itemsInfo, String itemStatus)
			throws Exception {
		boolean result;
		try {
			// Populating order history and OrderItems history items objects from orders object
			UserOrdersHistory orderHistory = new UserOrdersHistory(info.getstore2doorStores(), info.getUser(), new Date(),
					info.getPaymentType(), info.getTotalPrice());
			Set<UserOrderItemsHistory> userOrderItemsHistories = new HashSet<UserOrderItemsHistory>(0);
			userOrderItemsHistories.add(new UserOrderItemsHistory(itemsInfo.getCategoryItems(), orderHistory,
					itemsInfo.getItemQuantity(), itemsInfo.getItemPrice(),
					Constants.ITEMDELIVERED.getValue().equalsIgnoreCase(itemStatus) ? Constants.ITEMDELIVERED.getValue()
							: itemStatus,
					new Date(), new Date(),itemsInfo.getUserAddress()));
			
			orderHistory.setUserOrderItemsHistories(userOrderItemsHistories);
			userOrdersHistory.save(orderHistory);
			// send the push notification as async call to improve performance
			CompletableFuture.runAsync(()->{
				User userInfo = info.getUser();
				if (!JString.isEmpty(userInfo.getDeviceId()))
					try {
						utils.sendPushNotification("Your ordered item is deliverd ","Your Item is "+itemsInfo.getCategoryItems().getItemName()+" Delivered Succesfully", userInfo.getDeviceId());
					} catch (Exception e) {
						 mailerService.sendEmailOnException(env, "Exception is Form Mr Green Backend API", e);
					}
			});
			result = true;
		} catch (Exception exception) {
		
			throw exception;
		}
		return result;
	}
	@Override
	public List<MyOrdersDataDTO> cancelOrder(UserCartDTO dto, UserPrincipal currentUser) throws Exception {
		List<MyOrdersDataDTO> ordersDto = new ArrayList<MyOrdersDataDTO>();
		try {
			UserOrderItems orderItemsInfo = userOrderItems.findById(dto.getId()).get();
			UserOrders ordersInfo = orderItemsInfo.getUserOrders();
			// inserting the orders details into the orders history table
			saveOrdersHistoryData(ordersInfo, orderItemsInfo,Constants.ITEMCANCELLED.getValue());
			try {
				// deleting the the Order details in the orders table
				List<UserOrderItems> orderItemList = userOrderItems
						.findByUserOrders(new UserOrders(orderItemsInfo.getUserOrders().getId()));
				userOrderItems.deleteById(orderItemsInfo.getId());
				if (orderItemList.size() == 1)
					userOrders.deleteById(ordersInfo.getId());
			} catch (Exception exception) {
				throw exception;
			}
			ordersDto = loadOrderedHistoryForUser(currentUser);

		} catch (Exception exception) {
			throw exception;
		}
		return ordersDto;
	}
	@Override
	public boolean saveRating(UserCartDTO dto, UserPrincipal currentUser) throws Exception {
		boolean result;
		try {
		UserOrderItemsHistory itemHistoryInfo = userOrderItemHistory.findById(dto.getId()).get();
		itemHistoryInfo.setRating(dto.getRating());
		itemHistoryInfo.setComments(dto.getReviewComments());
		userOrderItemHistory.save(itemHistoryInfo);
		result = true;
		}catch (Exception exception) {
			throw exception;
		}
		
		return result;
	}
	@Override
	public ZipcodeDTO getAddressByPincode(String pincode) throws Exception {
		Zipcode zipCode=null;
		ZipcodeDTO dto=null;
		try {
			zipCode = zipcodeRepository.findByPincode(Integer.parseInt(pincode));
			if(zipCode!=null) {
				dto = modelMapper.map(zipCode, ZipcodeDTO.class);
			}else {
				JSONObject jsonObject = utils.getAddressFromUrl(pincode);
				if(jsonObject!=null) {
					zipCode=new Zipcode(Integer.parseInt(pincode), String.valueOf(jsonObject.get("District")), String.valueOf(jsonObject.get("Taluk")), String.valueOf(jsonObject.get("State")), String.valueOf(jsonObject.get("Country")));
					zipCode= zipcodeRepository.save(zipCode);
					dto = modelMapper.map(zipCode, ZipcodeDTO.class);
				}
			}
		}catch (Exception e) {
			throw e;
		}
		return dto;
	}
	@Override
	public DeliveryChargesDTO saveDeliveryCharges(DeliveryChargesDTO deliveryChargesDTO) throws Exception {
		try {
			DeliveryCharges deliveryCharges = modelMapper.map(deliveryChargesDTO, DeliveryCharges.class);
			deliveryCharges=deliveryChargesRepository.save(deliveryCharges);
			deliveryChargesDTO.setId(deliveryCharges.getId());
		}catch (Exception e) {
			throw e;
		}
		return deliveryChargesDTO;
	}
	@Override
	public DeliveryChargesDTO getDeliveryCharges() throws Exception {
		DeliveryChargesDTO deliveryChargesDTO=null;
		try {
			List<DeliveryCharges> deliveryChargesList = deliveryChargesRepository.findAll();
			if(deliveryChargesList.size()>0)
				deliveryChargesDTO= modelMapper.map(deliveryChargesList.get(0), DeliveryChargesDTO.class);
		}catch (Exception e) {
			throw e;
		}
		return deliveryChargesDTO;
	}
}
