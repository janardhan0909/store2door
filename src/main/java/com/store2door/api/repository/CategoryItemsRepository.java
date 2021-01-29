package com.store2door.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store2door.api.model.CategoryItems;
import com.store2door.api.model.StoreCategories;

import java.lang.String;

@Repository
public interface CategoryItemsRepository extends JpaRepository<CategoryItems, Long>{
	
	List<CategoryItems> findByStoreCategoriesInAndDeleteFlagOrderByDisplayOrderAsc(StoreCategories storeCategories,char deleteFlag);
	
	List<CategoryItems> findByItemName(String itemname);

}
