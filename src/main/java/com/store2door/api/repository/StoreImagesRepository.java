package com.store2door.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.StoresImages;

public interface StoreImagesRepository extends JpaRepository<StoresImages, Long> {
	
	List<StoresImages> findByStore2doorStoresId(long storeId);

}
