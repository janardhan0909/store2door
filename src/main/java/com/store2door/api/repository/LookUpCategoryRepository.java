package com.store2door.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.LookupCategory;

public interface LookUpCategoryRepository extends JpaRepository<LookupCategory, Long> {
	
	LookupCategory findByCategoryName(String categoryname);

}
