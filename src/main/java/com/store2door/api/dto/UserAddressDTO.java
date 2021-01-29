package com.store2door.api.dto;

public class UserAddressDTO {
	private long id;
	private String name;
	private String address;
	private String streetAddress;
	private String pin;
	private String state;
	private String contNo;
	private String city;
	private String landMark;
	public UserAddressDTO() {
		
	}
	
	public UserAddressDTO(long id, String name, String address, String streetAddress, String pin, String state,
			String contNo, String city,String landMark) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.streetAddress = streetAddress;
		this.pin = pin;
		this.state = state;
		this.contNo = contNo;
		this.city = city;
		this.landMark=landMark;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
}
