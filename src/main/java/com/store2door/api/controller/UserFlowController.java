package com.store2door.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store2door.api.dto.UserCartDTO;
import com.store2door.api.security.CurrentUser;
import com.store2door.api.security.UserPrincipal;
import com.store2door.api.serviceimpl.UserFlowServiceImpl;
import com.store2door.api.utils.Constants;
import com.store2door.api.utils.Store2doorUtils;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/userFlow")
public class UserFlowController {
	
	
	@Autowired
	private UserFlowServiceImpl userFlowServices;
	@Autowired
	Store2doorUtils utils;
	
	@PostMapping("/addCart")
	public JSONObject addCart(@RequestBody String cartData,@CurrentUser UserPrincipal currentUser,HttpServletRequest request)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			UserCartDTO dto = new ObjectMapper().readValue(cartData, UserCartDTO.class);
			List<UserCartDTO> addCart = userFlowServices.addCart(dto, currentUser);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("cartData", addCart);
		}catch(Exception exception) {
			exception.printStackTrace();
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	
	@PostMapping("/loadCart")
	public JSONObject loadAllCartForUser(@RequestBody String cartData,@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			 List<UserCartDTO> loadAllCartForUser = userFlowServices.loadAllCartForUser(currentUser);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("loadCartData", loadAllCartForUser);
		}catch(Exception exception) {
			exception.printStackTrace();
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}

	@PostMapping("/deleteItemFromCart")
	public JSONObject deleteItemCart(@RequestBody String cartData,@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			UserCartDTO dto = new ObjectMapper().readValue(cartData, UserCartDTO.class);
			List<UserCartDTO> cartItemsData = userFlowServices.deleteCartItem(dto, currentUser);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("loadCartData", cartItemsData);
		}catch(Exception exception) {
			exception.printStackTrace();
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	
	public JSONObject loadByItems(@RequestBody String itemName,@CurrentUser UserPrincipal currentUser)throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			
			jsonObject.put("data", userFlowServices.loadCategory(itemName));
			jsonObject.put("success", Constants.SUCCESS.isStatus());
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
			
		}
		return jsonObject;
	}
	
	
	
}
