package com.store2door.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.StoreCategoryImages;

public interface CategoryImagesRepository extends JpaRepository<StoreCategoryImages, Long> {
	
	     List<StoreCategoryImages> findByStoreCategoriesId(long categoryId);

}
