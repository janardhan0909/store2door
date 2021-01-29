package com.store2door.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_order_items_history", catalog = "store2door")
public class UserOrderItemsHistory implements java.io.Serializable {

	/**
	 * @author Janardhan
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private CategoryItems categoryItems;
	private UserOrdersHistory userOrdersHistory;
	private Integer itemQuantity;
	private UserAddress userAddress;
	private Double itemPrice;
	private String itemStatus;
	private Date createdTime;
	private Date lastUpdatedTime;
	private int rating;
	private String comments;
	public UserOrderItemsHistory() {
	}

	public UserOrderItemsHistory(long id, CategoryItems categoryItems, UserOrdersHistory userOrdersHistory) {
		this.id = id;
		this.categoryItems = categoryItems;
		this.userOrdersHistory = userOrdersHistory;
	}

	public UserOrderItemsHistory(long id, CategoryItems categoryItems, UserOrdersHistory userOrdersHistory,
			Integer itemQuantity, Double itemPrice, String itemStatus,Date lastUpdatedTime) {
		this.id = id;
		this.categoryItems = categoryItems;
		this.userOrdersHistory = userOrdersHistory;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.itemStatus = itemStatus;
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
	public UserOrderItemsHistory(CategoryItems categoryItems, UserAddress userAddress,UserOrdersHistory userOrdersHistory,
			Integer itemQuantity, Double itemPrice, String itemStatus,Date createdTime,Date lastUpdatedTime,int rating, String comments) {
		this.categoryItems = categoryItems;
		this.userAddress = userAddress;
		this.userOrdersHistory = userOrdersHistory;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.itemStatus = itemStatus;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.rating = rating;
		this.comments = comments;
	}
	
	public UserOrderItemsHistory(CategoryItems categoryItems,UserOrdersHistory userOrdersHistory,
			Integer itemQuantity, Double itemPrice, String itemStatus,Date createdTime,Date lastUpdatedTime,UserAddress userAddress) {
		this.categoryItems = categoryItems;
		this.userAddress = userAddress;
		this.userOrdersHistory = userOrdersHistory;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.itemStatus = itemStatus;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		
	}

	@Id

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
	public UserOrdersHistory getUserOrdersHistory() {
		return this.userOrdersHistory;
	}

	public void setUserOrdersHistory(UserOrdersHistory userOrdersHistory) {
		this.userOrdersHistory = userOrdersHistory;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_delivered_adderess")
	public UserAddress getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
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

	@Column(name = "rating", nullable = false)
	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Column(name = "comments", nullable = false, length = 50)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
