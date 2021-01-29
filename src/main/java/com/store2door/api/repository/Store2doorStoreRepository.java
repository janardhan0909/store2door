package com.store2door.api.repository;

import com.store2door.api.model.Store2doorStores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Store2doorStoreRepository extends JpaRepository<Store2doorStores, Long>{
	List<Store2doorStores> findByDeleteFlag(char deleteFlag);
}
