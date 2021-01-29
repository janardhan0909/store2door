package com.store2door.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.UserOrdersHistory;

public interface UserOrderHistoryRepository extends JpaRepository<UserOrdersHistory, Long> {
	List<UserOrdersHistory> findByUserIdOrderByOrderTimeDesc(long userId);
}
