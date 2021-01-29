package com.store2door.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.UserOrderItems;
import com.store2door.api.model.UserOrders;

import java.util.List;

public interface UserOrderItemsRepository extends JpaRepository<UserOrderItems, Long>{
	
	List<UserOrderItems> findByUserOrders(UserOrders userorders);

}
