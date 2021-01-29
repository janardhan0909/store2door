package com.store2door.api.service;

import java.util.List;

import com.store2door.api.dto.CategoryItemsDTO;
import com.store2door.api.dto.UserCartDTO;
import com.store2door.api.security.UserPrincipal;

public interface UserFlowService {
	
	public List<UserCartDTO> addCart(UserCartDTO dto,UserPrincipal currentUser)throws Exception;
	
	public List<UserCartDTO> loadAllCartForUser(UserPrincipal currentUser)throws Exception;
	
	public List<UserCartDTO> deleteCartItem(UserCartDTO dto,UserPrincipal currentUser)throws Exception;
	
	public List<CategoryItemsDTO> loadCategory(String itemName)throws Exception;

}
