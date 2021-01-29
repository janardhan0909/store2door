package com.store2door.api.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store2door.api.dto.LookUpItemDTO;
import com.store2door.api.service.LookUpCategoryService;
import com.store2door.api.utils.Constants;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/lookUp")
public class LookUpController {

	@Autowired
	private LookUpCategoryService lookUpCategoryServices;
	
	@PostMapping("/getCategoryItemByName")
	public JSONObject getCategoryItemByName(@RequestBody String categoryName) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			List<LookUpItemDTO> categoryByCategoryName = lookUpCategoryServices.getCategoryByCategoryName(categoryName.replaceAll("^\"|\"$", ""));
			jsonObject.put("orderStatus",categoryByCategoryName);
			jsonObject.put("success", Constants.SUCCESS.isStatus());
		} catch (Exception ex) {
			jsonObject.put("success", !Constants.SUCCESS.isStatus());
			throw ex;
		}
		return jsonObject;
	}
}
