package com.store2door.api.service;

import com.store2door.api.dto.CategoryItemsDTO;
import com.store2door.api.dto.CouponsDTO;
import com.store2door.api.dto.Store2doorStoresDTO;
import com.store2door.api.dto.StoreCategoriesDTO;
import com.store2door.api.security.UserPrincipal;

import java.util.List;

public interface StoresService {
	public List<Store2doorStoresDTO> loadAllStoresForSuperAdmin(char deleteFlag)throws Exception;
	public List<Store2doorStoresDTO> addStore(Store2doorStoresDTO dto)throws Exception;
	public List<StoreCategoriesDTO> loadAllCategoriesForStore(long storeId)throws Exception;
	public List<StoreCategoriesDTO> addCategory(StoreCategoriesDTO dto,UserPrincipal currentUser)throws Exception;
	public List<CategoryItemsDTO> loadAllItemsForStore(long categoryId)throws Exception;
	public List<CategoryItemsDTO> addItem(CategoryItemsDTO dto,UserPrincipal currentUser)throws Exception;
	public Store2doorStoresDTO loadSelectedStore(long storeId)throws Exception;
	public List<CategoryItemsDTO> loadAllItemsForSearch(long storeId)throws Exception;
	public List<CouponsDTO> saveCoupons(CouponsDTO[] couponsList)throws Exception;
	public List<CouponsDTO> loadCoupons()throws Exception;
}
