package com.store2door.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store2door.api.dto.CategoryItemsDTO;
import com.store2door.api.dto.CouponsDTO;
import com.store2door.api.dto.Store2doorStoresDTO;
import com.store2door.api.dto.StoreCategoriesDTO;
import com.store2door.api.security.CurrentUser;
import com.store2door.api.security.UserPrincipal;
import com.store2door.api.serviceimpl.StoresServiceImpl;
import com.store2door.api.utils.Constants;
import com.store2door.api.utils.JString;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoresController {
	@Autowired
	private StoresServiceImpl storesServiceImpl;
	
	@PostMapping("/addStore")
	public JSONObject addStore(@RequestBody String newStoreData)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			Store2doorStoresDTO dto = new ObjectMapper().readValue(newStoreData, Store2doorStoresDTO.class);
			List<Store2doorStoresDTO> store2doorStoresDTOs = storesServiceImpl.addStore(dto);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("storesList", store2doorStoresDTOs);
		}catch(Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/loadAllStores")
	public List<Store2doorStoresDTO> loadAllStores()throws Exception{
		List<Store2doorStoresDTO> store2doorStoresDTOs;
		try {
			store2doorStoresDTOs = storesServiceImpl.loadAllStoresForSuperAdmin('N');
		}catch(Exception exception) {
			throw exception;
		}
		return store2doorStoresDTOs;
	}
	@PostMapping("/loadAllCategoriesForStore")
	public JSONObject loadAllCategoriesForStore(HttpServletRequest request)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			String storeId = request.getParameter("id");
			if(!JString.isCompletlyEmpty(storeId)) {
				List<StoreCategoriesDTO> store2doorStoresDTOs = storesServiceImpl.loadAllCategoriesForStore(Long.parseLong(storeId));
				jsonObject.put("success", Constants.SUCCESS.isStatus());
				jsonObject.put("categoriesList", store2doorStoresDTOs);
			}else
				jsonObject.put("success", !Constants.SUCCESS.isStatus());
		}catch(Exception exception) {
			jsonObject.put("success",! Constants.SUCCESS.isStatus());
			exception.printStackTrace();
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/addCategory")
	public JSONObject addCategory(@RequestBody String categoryData,@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			StoreCategoriesDTO dto = new ObjectMapper().readValue(categoryData, StoreCategoriesDTO.class);
			List<StoreCategoriesDTO> store2doorStoresDTOs = storesServiceImpl.addCategory(dto, currentUser);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("categoriesList", store2doorStoresDTOs);
		}catch(Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			exception.printStackTrace();
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/loadAllItemsForStore")
	public JSONObject loadAllItemsForStore(HttpServletRequest request)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			//JSONObject infoObj = new ObjectMapper().readValue(info, JSONObject.class);
			String categoryId =request.getParameter("id");
			if(!JString.isCompletlyEmpty(categoryId)) {
				List<CategoryItemsDTO> store2doorStoresDTOs = storesServiceImpl.loadAllItemsForStore(Long.parseLong(categoryId));
				jsonObject.put("success", Constants.SUCCESS.isStatus());
				jsonObject.put("itemsList", store2doorStoresDTOs);
			}else
				jsonObject.put("success", !Constants.SUCCESS.isStatus());
		}catch(Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			exception.printStackTrace();
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/loadAllItemsForStore1")
	public JSONObject loadAllItemsForStore1(@RequestBody String info)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject infoObj = new ObjectMapper().readValue(info, JSONObject.class);
			long categoryId = Long.parseLong(String.valueOf(infoObj.get("categoryId")));
			List<CategoryItemsDTO> store2doorStoresDTOs = storesServiceImpl.loadAllItemsForStore(categoryId);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("itemsList", store2doorStoresDTOs);
		}catch(Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			exception.printStackTrace();
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/addItem")
	public JSONObject addItem(@RequestBody String itemData,@CurrentUser UserPrincipal currentUser)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			CategoryItemsDTO dto = new ObjectMapper().readValue(itemData, CategoryItemsDTO.class);
			List<CategoryItemsDTO> store2doorStoresDTOs = storesServiceImpl.addItem(dto, currentUser);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("itemsList", store2doorStoresDTOs);
		}catch(Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			exception.printStackTrace();
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/search")
	public JSONObject loadAllStroresForSearch(@RequestBody String info,@CurrentUser UserPrincipal currentUser)throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data", storesServiceImpl.loadAllItemsForSearch(Long.parseLong(info.replaceAll("^\"|\"$", ""))));
			jsonObject.put("success", Constants.SUCCESS.isStatus());
		}catch (Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/saveCoupons")
	public JSONObject saveCoupons(@RequestBody String data)throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			CouponsDTO[] dtoList = new ObjectMapper().readValue(data, CouponsDTO[].class);
			List<CouponsDTO> CouponsDTOList = storesServiceImpl.saveCoupons(dtoList);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("couponsList", CouponsDTOList);
		}catch(Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			exception.printStackTrace();
			throw exception;
		}
		return jsonObject;
	}
	@PostMapping("/loadCoupons")
	public JSONObject loadCoupons()throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			List<CouponsDTO> CouponsDTOList = storesServiceImpl.loadCoupons();
			jsonObject.put("success", Constants.SUCCESS.isStatus());
			jsonObject.put("couponsList", CouponsDTOList);
		}catch(Exception exception) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			exception.printStackTrace();
			throw exception;
		}
		return jsonObject;
	}
}
