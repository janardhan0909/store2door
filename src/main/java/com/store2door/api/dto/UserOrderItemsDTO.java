package com.store2door.api.dto;

public class UserOrderItemsDTO {
	
	private long id;
	private long itemId;
	private int itemQuantity;
	private double itemPrice;
	private long orderId;
	private String temStatus;
	
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
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getTemStatus() {
		return temStatus;
	}
	public void setTemStatus(String temStatus) {
		this.temStatus = temStatus;
	}
	
	
	
	

}
