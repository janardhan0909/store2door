package com.store2door.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
	
	List<UserAddress> findByUserId(long userId);
	List<UserAddress> findByUserIdOrderByCreatedTimeDesc(long userId);

}
