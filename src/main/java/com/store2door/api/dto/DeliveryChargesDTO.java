package com.store2door.api.dto;

public class DeliveryChargesDTO {
	private Long id;
	private double deliveryCharge;
	private double deliveryAmountLimit;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getDeliveryCharge() {
		return deliveryCharge;
	}
	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}
	public double getDeliveryAmountLimit() {
		return deliveryAmountLimit;
	}
	public void setDeliveryAmountLimit(double deliveryAmountLimit) {
		this.deliveryAmountLimit = deliveryAmountLimit;
	}
	
}
