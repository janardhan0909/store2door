package com.store2door.api.model;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zipcode", catalog = "store2door")
public class Zipcode implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private int pincode;
	private String district;
	private String city;
	private String state;
	private String country;

	public Zipcode() {
	}

	public Zipcode(int pincode, String district, String city, String state, String country) {
		this.pincode = pincode;
		this.district = district;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "pincode", nullable = false)
	public int getPincode() {
		return this.pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	@Column(name = "district", nullable = false, length = 100)
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "city", nullable = false, length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state", nullable = false, length = 100)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "country", nullable = false, length = 100)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
