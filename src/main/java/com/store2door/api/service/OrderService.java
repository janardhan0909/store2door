package com.store2door.api.service;

import java.util.List;

import com.store2door.api.dto.DeliveryChargesDTO;
import com.store2door.api.dto.MyOrdersDataDTO;
import com.store2door.api.dto.UserAddressDTO;
import com.store2door.api.dto.UserCartDTO;
import com.store2door.api.dto.ZipcodeDTO;
import com.store2door.api.model.UserOrderItems;
import com.store2door.api.model.UserOrders;
import com.store2door.api.security.UserPrincipal;

public interface OrderService {
	
	public UserAddressDTO addAddress(UserAddressDTO dto, UserPrincipal currentUser)throws Exception;
	
	public List<UserAddressDTO> getCurrentUserAddress(UserPrincipal currentUser)throws Exception;
	
	public boolean addOrderData(MyOrdersDataDTO dto,UserPrincipal currentUser)throws Exception;
	
	public List<MyOrdersDataDTO> loadOrdersForUser(UserPrincipal currentUser)throws Exception;
	
	public List<MyOrdersDataDTO> loadAllOrders(UserPrincipal currentUser)throws Exception;
	
	public List<MyOrdersDataDTO> orderStatusChanged(UserCartDTO dto ,UserPrincipal currentUser)throws Exception;
	
	public List<MyOrdersDataDTO> loadOrderedHistoryForUser(UserPrincipal currentUser) throws Exception;

	public boolean saveOrdersHistoryData(UserOrders info,UserOrderItems itemsinfo,String itemStatus)throws Exception;
	
	public List<MyOrdersDataDTO> cancelOrder(UserCartDTO dto,UserPrincipal currentUser)throws Exception;
	
	public boolean saveRating (UserCartDTO dto,UserPrincipal currentUser)throws Exception;
	
	public ZipcodeDTO getAddressByPincode(String pincode)throws Exception;
	
	public DeliveryChargesDTO saveDeliveryCharges(DeliveryChargesDTO deliveryChargesDTO)throws Exception;
	
	public DeliveryChargesDTO getDeliveryCharges()throws Exception;
}
