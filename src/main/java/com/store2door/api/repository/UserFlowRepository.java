package com.store2door.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.UserCart;

public interface UserFlowRepository extends JpaRepository<UserCart, Long> {
	
	List<UserCart> findByCategoryItemsId(long categoryItemId);
	
	UserCart findByUserIdInAndCategoryItemsId(long userId,long CategoryItemId);
	
	List<UserCart> findByUserId(long userId);

}
