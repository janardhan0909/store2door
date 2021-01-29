package com.store2door.api.repository;

import com.store2door.api.model.Store2doorStores;
import com.store2door.api.model.StoreCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreCategoriesRepository extends JpaRepository<StoreCategories, Long>{
	List<StoreCategories> findByStore2doorStoresInAndDeleteFlag(Store2doorStores stores, char deleteFlag);

}
