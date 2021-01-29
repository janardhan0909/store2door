package com.store2door.api.dto;

import java.util.Date;
import java.util.List;

public class MyOrdersDataDTO {
	private long id;
	private long orderForm;
	private String paymentType;
	private long totalPrice;
	private long orderAddressId;
	private List<UserCartDTO> orderItemsData;
	private Date orderedTime;
	private String orderStatus;
	private List<UserCartDTO> orderPlacedItems;
	private List<UserCartDTO> shippedItmes;
	private List<UserCartDTO> outForDelieryItems;
	private List<UserCartDTO> cancelledItems;
	private List<UserCartDTO> deliveredItems;
	
	public MyOrdersDataDTO() {
		
	}public MyOrdersDataDTO(long id,long orderForm,String paymentType,long totalPrice,long orderAddressId, Date orderedTime){
		this.id = id;
		this.orderAddressId = orderAddressId;
		this.orderForm = orderForm;
		this.paymentType = paymentType;
		this.totalPrice = totalPrice;
		this.orderedTime = orderedTime;
	}
	
	public long getOrderForm() {
		return orderForm;
	}
	public void setOrderForm(long orderForm) {
		this.orderForm = orderForm;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public long getOrderAddressId() {
		return orderAddressId;
	}
	public void setOrderAddressId(long orderAddressId) {
		this.orderAddressId = orderAddressId;
	}
	public List<UserCartDTO> getOrderItemsData() {
		return orderItemsData;
	}
	public void setOrderItemsData(List<UserCartDTO> orderItemsData) {
		this.orderItemsData = orderItemsData;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getOrderedTime() {
		return orderedTime;
	}
	public void setOrderedTime(Date orderedTime) {
		this.orderedTime = orderedTime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public List<UserCartDTO> getOrderPlacedItems() {
		return orderPlacedItems;
	}
	public void setOrderPlacedItems(List<UserCartDTO> orderPlacedItems) {
		this.orderPlacedItems = orderPlacedItems;
	}
	
	public List<UserCartDTO> getShippedItmes() {
		return shippedItmes;
	}
	public void setShippedItmes(List<UserCartDTO> shippedItmes) {
		this.shippedItmes = shippedItmes;
	}
	public List<UserCartDTO> getOutForDelieryItems() {
		return outForDelieryItems;
	}
	public void setOutForDelieryItems(List<UserCartDTO> outForDelieryItems) {
		this.outForDelieryItems = outForDelieryItems;
	}
	public List<UserCartDTO> getCancelledItems() {
		return cancelledItems;
	}
	public void setCancelledItems(List<UserCartDTO> cancelledItems) {
		this.cancelledItems = cancelledItems;
	}
	public List<UserCartDTO> getDeliveredItems() {
		return deliveredItems;
	}
	public void setDeliveredItems(List<UserCartDTO> deliveredItems) {
		this.deliveredItems = deliveredItems;
	}
	
}
