package com.store2door.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.StoreCategoryItemImages;

public interface CateroryItemImagesRepository extends JpaRepository<StoreCategoryItemImages, Long>{
	
	List<StoreCategoryItemImages> findByCategoryItemsId(long categoryItemId);

}
