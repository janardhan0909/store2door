package com.store2door.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.LookupItem;

public interface LookUpItemRepository extends JpaRepository<LookupItem, Long> {
	
	List<LookupItem> findByLookupCategoryIdOrderByDisplayOrderAsc(long lookUpCategoryId);

}
