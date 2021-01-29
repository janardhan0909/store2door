package com.store2door.api.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.store2door.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store2door.api.dto.CategoryItemsDTO;
import com.store2door.api.dto.UserCartDTO;
import com.store2door.api.model.Store2doorStores;
import com.store2door.api.repository.CategoryItemsRepository;
import com.store2door.api.repository.CateroryItemImagesRepository;
import com.store2door.api.repository.UserFlowRepository;
import com.store2door.api.security.UserPrincipal;
import com.store2door.api.service.UserFlowService;
import com.store2door.api.utils.JString;

@Service
public class UserFlowServiceImpl implements UserFlowService {

	@Autowired
	private UserFlowRepository userCartRepository;
	@Autowired
	private CategoryItemsRepository categoryItems;
	@Autowired
	CateroryItemImagesRepository cateroryItemImagesRepository;

	@Override
	public List<UserCartDTO> addCart(UserCartDTO dto, UserPrincipal currentUser) throws Exception {
		List<UserCartDTO> loadAllCartForUser = null;
		UserCart userCart = null;
		try {
			CategoryItems itemInfo = new CategoryItems();
			itemInfo.setId(dto.getItemId());
			userCart = userCartRepository.findByUserIdInAndCategoryItemsId(currentUser.getId(), dto.getItemId());
			// save the cart data
			if (JString.isEmpty(userCart)) {
				userCart = new UserCart(itemInfo, new Store2doorStores(dto.getStoreId()), new User(currentUser.getId()),
						dto.getItemQuantity(), dto.getTotalPrice(), new Date(), new Date());
			} else {
				userCart.setItemQuantity(dto.getItemQuantity());
			}
			userCartRepository.save(userCart);
			if (!JString.isEmpty(userCart)) {
				loadAllCartForUser = loadAllCartForUser(currentUser);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return loadAllCartForUser;
	}

	@Override
	public List<UserCartDTO> loadAllCartForUser(UserPrincipal currentUser) throws Exception {
		List<UserCartDTO> userCartDtos = new ArrayList<UserCartDTO>();
		try {
			List<UserCart> findByUserId = userCartRepository.findByUserId(currentUser.getId());
			for (UserCart userCartInfo : findByUserId) {
				UserCartDTO dto = new UserCartDTO(userCartInfo.getId(), userCartInfo.getCategoryItems().getId(),
						userCartInfo.getCategoryItems().getItemName(), userCartInfo.getstore2doorStores().getId(),
						userCartInfo.getstore2doorStores().getStoreName(), userCartInfo.getItemQuantity(),
						userCartInfo.getTotalPrice(), "", userCartInfo.getCreatedTime(), userCartInfo.getUpdatedTime(),"",userCartInfo.getCategoryItems().getUnitofMesure());
				for (StoreCategoryItemImages images :cateroryItemImagesRepository.findByCategoryItemsId(userCartInfo.getCategoryItems().getId())) {
					dto.setBase64Image(images.getItemImage());
				}
				userCartDtos.add(dto);
			}
		} catch (Exception exception) {
			throw exception;
		}
		return userCartDtos;
	}

	@Override
	public List<UserCartDTO> deleteCartItem(UserCartDTO dto, UserPrincipal currentUser) throws Exception {
		List<UserCartDTO> loadAllCartForUser = null;
		try {
			userCartRepository.deleteById(dto.getId());
			loadAllCartForUser = loadAllCartForUser(currentUser);
		} catch (Exception exception) {
			throw exception;
		}
		return loadAllCartForUser;

	}

	@Override
	public List<CategoryItemsDTO> loadCategory(String categoryId) throws Exception {
		List<CategoryItemsDTO> dtos = new ArrayList<CategoryItemsDTO>();
		try {
			dtos = categoryItems.findByItemName(categoryId).stream()
					.map(item -> new CategoryItemsDTO(item.getId(), item.getItemName(), item.getItemDescription(),
							item.getItemPrice(), item.getItemQuantity(), item.getStoreCategoryItemImageses().stream()
									.map(data -> new String(data.getItemImage())).collect(Collectors.toList())))
					.collect(Collectors.toList());

		} catch (Exception e) {
			throw e;
		}
		return dtos;
	}

}
