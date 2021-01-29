package com.store2door.api.dto;

import java.util.Date;

public class CouponsDTO {
	private Long id;
	private String couponCode;
	private Date expiryDate;
	private double couponOffer;
	private String couponType;
	private double maximumLimit;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public double getCouponOffer() {
		return couponOffer;
	}
	public void setCouponOffer(double couponOffer) {
		this.couponOffer = couponOffer;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public double getMaximumLimit() {
		return maximumLimit;
	}
	public void setMaximumLimit(double maximumLimit) {
		this.maximumLimit = maximumLimit;
	}
	public CouponsDTO(Long id, String couponCode, Date expiryDate, double couponOffer, String couponType,double maximumLimit) {
		this.id = id;
		this.couponCode = couponCode;
		this.expiryDate = expiryDate;
		this.couponOffer = couponOffer;
		this.couponType = couponType;
		this.maximumLimit = maximumLimit;
	}
	public CouponsDTO() {
	}
}
