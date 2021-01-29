package com.store2door.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.UserOrders;

public interface UserOrdersRepository extends JpaRepository<UserOrders, Long> {
	
	List<UserOrders> findByUserId(long userId);

}
