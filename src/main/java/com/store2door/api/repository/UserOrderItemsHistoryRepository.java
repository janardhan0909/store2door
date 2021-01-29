package com.store2door.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store2door.api.model.UserOrderItemsHistory;

public interface UserOrderItemsHistoryRepository extends JpaRepository<UserOrderItemsHistory, Long> {

}
