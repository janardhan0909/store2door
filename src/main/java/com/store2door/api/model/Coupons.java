package com.store2door.api.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "coupons", catalog = "store2door")
public class Coupons implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String couponCode;
	private Date expiryDate;
	private double couponOffer;
	private String couponType;
	private double maximumLimit;
	public Coupons() {
	}

	public Coupons(String couponCode, double couponOffer) {
		this.couponCode = couponCode;
		this.couponOffer = couponOffer;
	}

	public Coupons(String couponCode, Date expiryDate, double couponOffer,String couponType,double maximumLimit) {
		this.couponCode = couponCode;
		this.expiryDate = expiryDate;
		this.couponOffer = couponOffer;
		this.couponType=couponType;
		this.maximumLimit=maximumLimit;
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

	@Column(name = "coupon_code", nullable = false, length = 50)
	public String getCouponCode() {
		return this.couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiry_date", length = 19)
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name = "coupon_offer", nullable = false, precision = 22, scale = 0)
	public double getCouponOffer() {
		return this.couponOffer;
	}

	public void setCouponOffer(double couponOffer) {
		this.couponOffer = couponOffer;
	}
	@Column(name = "coupon_type")
	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	@Column(name = "maximum_limit")
	public double getMaximumLimit() {
		return maximumLimit;
	}

	public void setMaximumLimit(double maximumLimit) {
		this.maximumLimit = maximumLimit;
	}

}
