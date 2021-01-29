package com.store2door.api.service;

import java.util.List;

import com.store2door.api.dto.LookUpItemDTO;

public interface LookUpCategoryService {
	
	public List<LookUpItemDTO> getCategoryByCategoryName(String categoryName) throws Exception;

}
