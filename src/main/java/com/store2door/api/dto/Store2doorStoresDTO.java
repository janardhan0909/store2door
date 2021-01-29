package com.store2door.api.dto;

import java.util.List;

public class Store2doorStoresDTO {

	private long id;
	private String storeName;
	private String storeLocation;
	private String storePhoneNumber;
	private String storeEmail;
	private char deleteFlag;
	private long storeAdminId;
	private long userId;
	private List<String> storeImages;
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getStoreAdminId() {
		return storeAdminId;
	}
	public void setStoreAdminId(long storeAdminId) {
		this.storeAdminId = storeAdminId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreLocation() {
		return storeLocation;
	}
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	public String getStorePhoneNumber() {
		return storePhoneNumber;
	}
	public void setStorePhoneNumber(String storePhoneNumber) {
		this.storePhoneNumber = storePhoneNumber;
	}
	public String getStoreEmail() {
		return storeEmail;
	}
	public void setStoreEmail(String storeEmail) {
		this.storeEmail = storeEmail;
	}
	public char getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(char deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public List<String> getStoreImages() {
		return storeImages;
	}
	public void setStoreImages(List<String> storeImages) {
		this.storeImages = storeImages;
	}

}
