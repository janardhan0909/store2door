package com.store2door.api.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store2door.api.dto.DeliveryChargesDTO;
import com.store2door.api.dto.MyOrdersDataDTO;
import com.store2door.api.dto.UserAddressDTO;
import com.store2door.api.dto.UserCartDTO;
import com.store2door.api.security.CurrentUser;
import com.store2door.api.security.UserPrincipal;
import com.store2door.api.serviceimpl.OrderServiceImpl;
import com.store2door.api.utils.Constants;
import com.store2door.api.utils.JString;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderService;
	
	@PostMapping("/addNewAddress")
	public JSONObject addNewAddress(@RequestBody String addressDto,@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try{
			UserAddressDTO dto = new ObjectMapper().readValue(addressDto, UserAddressDTO.class);
			UserAddressDTO addAddress = orderService.addAddress(dto, currentUser);
			if(!JString.isEmpty(addAddress)) {
				jsonObject.put("success", Constants.SUCCESS.isStatus());
				jsonObject.put("address", addAddress);
			}else {
				jsonObject.put("success", !Constants.SUCCESS.isStatus());
			}
		}catch (Exception exception) {
			jsonObject.put("result", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/loadAddress")
	public JSONObject getAllAddress(@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			List<UserAddressDTO> currentUserAddress = orderService.getCurrentUserAddress(currentUser);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("address", currentUserAddress);
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/saveOrders")
	public JSONObject addUserOrderDetails(@RequestBody String orderData,@CurrentUser UserPrincipal currentUser)throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			MyOrdersDataDTO dto = new ObjectMapper().readValue(orderData, MyOrdersDataDTO.class);
			jsonObject.put("success", orderService.addOrderData(dto, currentUser));
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
		
	}
	@PostMapping("/loadOrderedDetails")
	public JSONObject loadOrderedForUser(@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data", orderService.loadOrdersForUser(currentUser));
			jsonObject.put("success", Constants.SUCCESS.isStatus());
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	
	@PostMapping("/cancelOrder")
	public JSONObject cancelOrder(@RequestBody String orderData,@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			UserCartDTO dto = new ObjectMapper().readValue(orderData, UserCartDTO.class);
			jsonObject.put("data", orderService.cancelOrder(dto, currentUser));
			jsonObject.put("success", Constants.SUCCESS.isStatus());
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/loadAllOrders")
	public JSONObject loadAllOrders(@RequestBody String orderData,@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data",  orderService.loadAllOrders(currentUser));
			jsonObject.put("success", Constants.SUCCESS.isStatus());
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/changeOrderStatus")
	public JSONObject changeOrderStatus(@RequestBody String orderData,@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			UserCartDTO dto = new ObjectMapper().readValue(orderData, UserCartDTO.class);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("data", orderService.orderStatusChanged(dto, currentUser));
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
		
	}
	
	@PostMapping("/loadOrderedHistoryDetails")
	public JSONObject loadOrderedHistoryForUser(@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data", orderService.loadOrderedHistoryForUser(currentUser));
			jsonObject.put("success", Constants.SUCCESS.isStatus());
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/saveRating")
	public JSONObject saveRatingData(@RequestBody String orderData,@CurrentUser UserPrincipal currentUser)throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			UserCartDTO dto = new ObjectMapper().readValue(orderData, UserCartDTO.class);
			jsonObject.put("success", orderService.saveRating(dto, currentUser));
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/getAddressByPin")
	public JSONObject getAddressByPincode(@RequestBody String pincode)throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data", orderService.getAddressByPincode(pincode.replaceAll("^\"|\"$", "")));
			jsonObject.put("success", Constants.SUCCESS.isStatus());
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/saveDeliveryCharges")
	public JSONObject saveDeliveryCharges(@RequestBody String data)throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data", orderService.saveDeliveryCharges(new ObjectMapper().readValue(data, DeliveryChargesDTO.class)));
			jsonObject.put("success", Constants.SUCCESS.isStatus());
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/getDeliveryCharges")
	public JSONObject getDeliveryCharges()throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data", orderService.getDeliveryCharges());
			jsonObject.put("success", Constants.SUCCESS.isStatus());
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}

}
