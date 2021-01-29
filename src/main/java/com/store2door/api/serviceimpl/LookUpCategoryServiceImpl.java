package com.store2door.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store2door.api.dto.LookUpItemDTO;
import com.store2door.api.model.LookupCategory;
import com.store2door.api.model.LookupItem;
import com.store2door.api.repository.LookUpCategoryRepository;
import com.store2door.api.repository.LookUpItemRepository;
import com.store2door.api.service.LookUpCategoryService;
import com.store2door.api.utils.JString;
@Service
public class LookUpCategoryServiceImpl implements LookUpCategoryService {

	@Autowired
	private LookUpCategoryRepository lookUpCategoryRepository;
	@Autowired
	private LookUpItemRepository lookUpItem;
	@Override
	public List<LookUpItemDTO> getCategoryByCategoryName(String categoryName) throws Exception {
		List<LookUpItemDTO> lookUpItemDtos = new ArrayList<LookUpItemDTO>();
		try {
			LookupCategory categoryInfo = lookUpCategoryRepository.findByCategoryName(categoryName);
			if(!JString.isEmpty(categoryInfo)) {
				List<LookupItem> lookUpItemList = lookUpItem.findByLookupCategoryIdOrderByDisplayOrderAsc(categoryInfo.getId());
				lookUpItemList.forEach(data -> {
					LookUpItemDTO dto = new LookUpItemDTO(data.getId(), data.getItemKey(), data.getItemValue(), String.valueOf(data.getDisplayOrder()),data.getLookupCategory().getId());
					lookUpItemDtos.add(dto);
				});
			}
		}catch (Exception exception) {
			throw exception;
		}
		return lookUpItemDtos;
	}

}
