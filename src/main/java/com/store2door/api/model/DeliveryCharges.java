package com.store2door.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_charges", catalog = "store2door")
public class DeliveryCharges implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private double deliveryCharge;
	private double deliveryAmountLimit;

	public DeliveryCharges() {
	}

	public DeliveryCharges(double deliveryCharge, double deliveryAmountLimit) {
		this.deliveryCharge = deliveryCharge;
		this.deliveryAmountLimit = deliveryAmountLimit;
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

	@Column(name = "delivery_charge", nullable = false, precision = 22, scale = 0)
	public double getDeliveryCharge() {
		return this.deliveryCharge;
	}

	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	@Column(name = "delivery_amount_limit", nullable = false, precision = 22, scale = 0)
	public double getDeliveryAmountLimit() {
		return this.deliveryAmountLimit;
	}

	public void setDeliveryAmountLimit(double deliveryAmountLimit) {
		this.deliveryAmountLimit = deliveryAmountLimit;
	}

}
