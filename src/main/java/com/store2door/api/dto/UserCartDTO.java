package com.store2door.api.dto;

import java.util.Date;

public class UserCartDTO {

	private long id;
	private long itemId;
	private String categoryItemName;
	private long storeId;
	private String store2doorStoreName;
	private int itemQuantity;
	private double totalPrice;
	private String base64Image;
	private Date createdTime;
	private Date lastUpdateTime;
	private String itemStatus;
	private UserAddressDTO deliveryAddress;
	private int rating;
	private String reviewComments;
	private String unitofMesure;

	public UserCartDTO() {

	}

	public UserCartDTO(long id, long itemId, String categoryItemName, long storeId, String store2doorStoreName,
			int itemQuantity, double totalPrice,String base64Image,Date createdTime,Date lastUpdateTime,String itemStatus,UserAddressDTO deliveryAddress,int rating,String reviewComments,String unitofMesure) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.categoryItemName = categoryItemName;
		this.storeId = storeId;
		this.store2doorStoreName = store2doorStoreName;
		this.itemQuantity = itemQuantity;
		this.totalPrice = totalPrice;
		this.base64Image = base64Image;
		this.createdTime =createdTime;
		this.lastUpdateTime = lastUpdateTime;
		this.itemStatus = itemStatus;
		this.deliveryAddress = deliveryAddress;
		this.rating = rating;
		this.reviewComments = reviewComments;
		this.unitofMesure=unitofMesure;
	}
	
	public UserCartDTO(long id, long itemId, String categoryItemName, long storeId, String store2doorStoreName,
			int itemQuantity, double totalPrice,String base64Image,Date createdTime,Date lastUpdateTime,String itemStatus,String unitofMesure) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.categoryItemName = categoryItemName;
		this.storeId = storeId;
		this.store2doorStoreName = store2doorStoreName;
		this.itemQuantity = itemQuantity;
		this.totalPrice = totalPrice;
		this.base64Image = base64Image;
		this.createdTime =createdTime;
		this.lastUpdateTime = lastUpdateTime;
		this.itemStatus = itemStatus;
		this.unitofMesure=unitofMesure;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCategoryItemName() {
		return categoryItemName;
	}

	public void setCategoryItemName(String categoryItemName) {
		this.categoryItemName = categoryItemName;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getstore2doorStoreName() {
		return store2doorStoreName;
	}

	public void setstore2doorStoreName(String store2doorStoreName) {
		this.store2doorStoreName = store2doorStoreName;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public UserAddressDTO getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(UserAddressDTO deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewComments() {
		return reviewComments;
	}

	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}

	public String getUnitofMesure() {
		return unitofMesure;
	}

	public void setUnitofMesure(String unitofMesure) {
		this.unitofMesure = unitofMesure;
	}

	
	
	
}
