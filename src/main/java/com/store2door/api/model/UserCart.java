package com.store2door.api.model;


import java.util.Date;
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

@Entity
@Table(name = "user_cart", catalog = "store2door")
public class UserCart implements java.io.Serializable {

	/**
	 *@author Janardhan
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private CategoryItems categoryItems;
	private Store2doorStores store2doorStores;
	private User user;
	private int itemQuantity;
	private double totalPrice;
	private Date createdTime;
	private Date updatedTime;

	public UserCart() {
	}
	public UserCart(long id) {
		this.id = id;
	}

	public UserCart(long id, CategoryItems categoryItems,Store2doorStores store2doorStores, User user, int itemQuantity, double totalPrice,
			Date createdTime, Date updatedTime) {
		this.id = id;
		this.categoryItems = categoryItems;
		this.user = user;
		this.itemQuantity = itemQuantity;
		this.totalPrice = totalPrice;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.store2doorStores = store2doorStores;
	}
	
	public UserCart( CategoryItems categoryItems,Store2doorStores store2doorStores, User user, int itemQuantity, double totalPrice,
			Date createdTime, Date updatedTime) {
		
		this.categoryItems = categoryItems;
		this.user = user;
		this.itemQuantity = itemQuantity;
		this.totalPrice = totalPrice;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.store2doorStores = store2doorStores;
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
	@JoinColumn(name = "added_by", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "item_quantity", nullable = false)
	public int getItemQuantity() {
		return this.itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	@Column(name = "total_price", nullable = false, precision = 22, scale = 0)
	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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
	@Column(name = "updated_time", nullable = false, length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	public Store2doorStores getstore2doorStores() {
		return this.store2doorStores;
	}

	public void setstore2doorStores(Store2doorStores store2doorStores) {
		this.store2doorStores = store2doorStores;
	}

}
