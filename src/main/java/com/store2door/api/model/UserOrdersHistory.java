package com.store2door.api.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_orders_history", catalog = "store2door")
public class UserOrdersHistory implements java.io.Serializable {

	/**
	 * @author Janardhan
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Store2doorStores store2doorStores;
	private User user;
	private Date orderTime;
	private String paymentType;
	private double totalPrice;
	private Set<UserOrderItemsHistory> userOrderItemsHistories = new HashSet<UserOrderItemsHistory>(0);

	public UserOrdersHistory() {
	}

	public UserOrdersHistory(long id, Store2doorStores store2doorStores, User user, Date orderTime, String paymentType,
			double totalPrice) {
		this.id = id;
		this.store2doorStores = store2doorStores;
		this.user = user;
		this.orderTime = orderTime;
		this.paymentType = paymentType;
		this.totalPrice = totalPrice;
	}

	public UserOrdersHistory(long id, Store2doorStores store2doorStores, User user, Date orderTime, String paymentType,
			double totalPrice, Set<UserOrderItemsHistory> userOrderItemsHistories) {
		this.id = id;
		this.store2doorStores = store2doorStores;
		this.user = user;
		this.orderTime = orderTime;
		this.paymentType = paymentType;
		this.totalPrice = totalPrice;
		this.userOrderItemsHistories = userOrderItemsHistories;
	}
	public UserOrdersHistory(Store2doorStores store2doorStores, User user, Date orderTime, String paymentType,
			double totalPrice) {
		this.store2doorStores = store2doorStores;
		this.user = user;
		this.orderTime = orderTime;
		this.paymentType = paymentType;
		this.totalPrice = totalPrice;
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordered_from", nullable = false)
	public Store2doorStores getstore2doorStores() {
		return this.store2doorStores;
	}

	public void setstore2doorStores(Store2doorStores store2doorStores) {
		this.store2doorStores = store2doorStores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_by", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_time", nullable = false, length = 19)
	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	@Column(name = "payment_type", nullable = false, length = 50)
	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "total_price", nullable = false, precision = 22, scale = 0)
	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userOrdersHistory",cascade=CascadeType.ALL)
	public Set<UserOrderItemsHistory> getUserOrderItemsHistories() {
		return this.userOrderItemsHistories;
	}

	public void setUserOrderItemsHistories(Set<UserOrderItemsHistory> userOrderItemsHistories) {
		this.userOrderItemsHistories = userOrderItemsHistories;
	}

}
