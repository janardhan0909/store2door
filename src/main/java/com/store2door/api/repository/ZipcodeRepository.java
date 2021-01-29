package com.store2door.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store2door.api.model.Zipcode;
@Repository
public interface ZipcodeRepository extends JpaRepository<Zipcode, Long>  {
	Zipcode findByPincode(int pincode);
}
