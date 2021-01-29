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

/**
 *  @author Mahesh Yadav
 */
@Entity
@Table(name = "category_items", catalog = "store2door")
public class CategoryItems implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private StoreCategories storeCategories;
	private User UserByLastUpdatedBy;
	private User UserByCreatedBy;
	private String itemName;
	private String itemDescription;
	private double itemPrice;
	private double itemQuantity;
	private Date createdTime;
	private Date lastUpdatedTime;
	private char deleteFlag;
	private String unitofMesure;
	private double marketPrice;
	private int displayOrder;
	private Set<StoreCategoryItemImages> storeCategoryItemImageses = new HashSet<StoreCategoryItemImages>(0);
	private Set<UserOrderItems> userOrderItemses = new HashSet<UserOrderItems>(0);
	private Set<UserCart> userCarts = new HashSet<UserCart>(0);
	private Set<UserOrderItemsHistory> userOrderItemsHistories = new HashSet<UserOrderItemsHistory>(0);
	public CategoryItems() {
	}
	public CategoryItems(long id) {
		this.id = id;
	}
	public CategoryItems(long id, StoreCategories storeCategories, User UserByLastUpdatedBy, User UserByCreatedBy,
			String itemName, double itemPrice, int itemQuantity, Date createdTime, Date lastUpdatedTime,
			char deleteFlag) {
		this.id = id;
		this.storeCategories = storeCategories;
		this.UserByLastUpdatedBy = UserByLastUpdatedBy;
		this.UserByCreatedBy = UserByCreatedBy;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.deleteFlag = deleteFlag;
	}

	public CategoryItems(long id, StoreCategories storeCategories, User UserByLastUpdatedBy, User UserByCreatedBy,
			String itemName, String itemDescription, double itemPrice, int itemQuantity, Date createdTime,
			Date lastUpdatedTime, char deleteFlag,Set<StoreCategoryItemImages> storeCategoryItemImageses,Set<UserOrderItems> userOrderItemses, Set<UserCart> userCarts,
			Set<UserOrderItemsHistory> userOrderItemsHistories) {
		this.id = id;
		this.storeCategories = storeCategories;
		this.UserByLastUpdatedBy = UserByLastUpdatedBy;
		this.UserByCreatedBy = UserByCreatedBy;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.deleteFlag = deleteFlag;
		this.storeCategoryItemImageses = storeCategoryItemImageses;
		this.userOrderItemses = userOrderItemses;
		this.userCarts = userCarts;
		this.userOrderItemsHistories = userOrderItemsHistories;
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
	@JoinColumn(name = "category_id", nullable = false)
	public StoreCategories getStoreCategories() {
		return this.storeCategories;
	}

	public void setStoreCategories(StoreCategories storeCategories) {
		this.storeCategories = storeCategories;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "last_updated_by", nullable = false)
	public User getUserByLastUpdatedBy() {
		return this.UserByLastUpdatedBy;
	}

	public void setUserByLastUpdatedBy(User UserByLastUpdatedBy) {
		this.UserByLastUpdatedBy = UserByLastUpdatedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	public User getUserByCreatedBy() {
		return this.UserByCreatedBy;
	}

	public void setUserByCreatedBy(User UserByCreatedBy) {
		this.UserByCreatedBy = UserByCreatedBy;
	}

	@Column(name = "item_name", nullable = false, length = 100)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "item_description", length = 200)
	public String getItemDescription() {
		return this.itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Column(name = "item_price", nullable = false, precision = 22, scale = 0)
	public double getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Column(name = "item_quantity", nullable = false)
	public double getItemQuantity() {
		return this.itemQuantity;
	}

	public void setItemQuantity(double itemQuantity) {
		this.itemQuantity = itemQuantity;
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

	@Column(name = "delete_flag", nullable = false, length = 1)
	public char getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(char deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryItems",cascade=CascadeType.ALL)
	public Set<StoreCategoryItemImages> getStoreCategoryItemImageses() {
		return this.storeCategoryItemImageses;
	}

	public void setStoreCategoryItemImageses(Set<StoreCategoryItemImages> storeCategoryItemImageses) {
		this.storeCategoryItemImageses = storeCategoryItemImageses;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryItems",cascade=CascadeType.ALL)
	public Set<UserOrderItems> getUserOrderItemses() {
		return this.userOrderItemses;
	}

	public void setUserOrderItemses(Set<UserOrderItems> userOrderItemses) {
		this.userOrderItemses = userOrderItemses;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryItems")
	public Set<UserCart> getUserCarts() {
		return this.userCarts;
	}

	public void setUserCarts(Set<UserCart> userCarts) {
		this.userCarts = userCarts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryItems")
	public Set<UserOrderItemsHistory> getUserOrderItemsHistories() {
		return this.userOrderItemsHistories;
	}

	public void setUserOrderItemsHistories(Set<UserOrderItemsHistory> userOrderItemsHistories) {
		this.userOrderItemsHistories = userOrderItemsHistories;
	}
	@Column(name = "unit_of_mesurement")
	public String getUnitofMesure() {
		return unitofMesure;
	}
	public void setUnitofMesure(String unitofMesure) {
		this.unitofMesure = unitofMesure;
	}
	@Column(name = "market_price")
	public double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}
	@Column(name="display_order")
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
}
