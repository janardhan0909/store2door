package com.store2door.api.model;

import java.util.Date;

// default package


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Janardhan
 */
@Entity
@Table(name = "user_order_items", catalog = "store2door")
public class UserOrderItems implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private CategoryItems categoryItems;
	private UserOrders userOrders;
	private UserAddress userAddress;
	private Integer itemQuantity;
	private Double itemPrice;
	private String itemStatus;
	private Date createdTime;
	private Date lastUpdatedTime;

	public UserOrderItems() {
	}

	public UserOrderItems(CategoryItems categoryItems, UserOrders userOrders, Integer itemQuantity,
			Double itemPrice, String itemStatus,Date createdTime,Date lastUpdatedTime,UserAddress userAddress) {
		this.categoryItems = categoryItems;
		this.userOrders =userOrders; 
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.itemStatus = itemStatus;
		this.lastUpdatedTime = lastUpdatedTime;
		this.createdTime = createdTime;
		this.userAddress = userAddress;
	}
	public UserOrderItems(long id, CategoryItems categoryItems, UserOrders userOrders) {
		this.id = id;
		this.categoryItems = categoryItems;
		this.userOrders = userOrders;
	}

	public UserOrderItems(long id, CategoryItems categoryItems, UserOrders userOrders, Integer itemQuantity,
			Double itemPrice, String itemStatus) {
		this.id = id;
		this.categoryItems = categoryItems;
		this.userOrders = userOrders;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.itemStatus = itemStatus;
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
	@JoinColumn(name = "item_id", nullable = false)
	public CategoryItems getCategoryItems() {
		return this.categoryItems;
	}

	public void setCategoryItems(CategoryItems categoryItems) {
		this.categoryItems = categoryItems;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	public UserOrders getUserOrders() {
		return this.userOrders;
	}

	public void setUserOrders(UserOrders userOrders) {
		this.userOrders = userOrders;
	}

	@Column(name = "item_quantity")
	public Integer getItemQuantity() {
		return this.itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	@Column(name = "item_price", precision = 22, scale = 0)
	public Double getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_delivery_address")
	public UserAddress getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	@Column(name = "item_status", length = 50)
	public String getItemStatus() {
		return this.itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", nullable = false, length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_time", nullable = false, length = 19)
	public Date getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

}
