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
@Table(name = "store2door_stores", catalog = "store2door")
public class Store2doorStores implements java.io.Serializable {

	/**
	 * @author Mahesh Yadav
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private User userByCreatedBy;
	private User userByLastUpdatedBy;
	private String storeName;
	private String storeLocation;
	private String storePhoneNumber;
	private String storeEmail;
	private Date createdTime;
	private Date lastUpdatedTime;
	private char deleteFlag;
	private Set<StoreAdmin> storeAdmins = new HashSet<StoreAdmin>(0);
	private Set<StoreCategories> storeCategorieses = new HashSet<StoreCategories>(0);
	private Set<StoresImages> storeImages = new HashSet<StoresImages>(0);
	private Set<UserOrdersHistory> userOrdersHistories = new HashSet<UserOrdersHistory>(0);
	private Set<UserOrders> userOrderses = new HashSet<UserOrders>(0);
	private Set<UserCart> userCarts = new HashSet<UserCart>(0);

	public Store2doorStores() {
	}
	public Store2doorStores(long id) {
		this.id = id;
	}
	public Store2doorStores(long id, User userByLastUpdatedBy, String storeName, String storePhoneNumber,
			String storeEmail, Date createdTime, Date lastUpdatedTime, char deleteFlag) {
		this.id = id;
		this.userByLastUpdatedBy = userByLastUpdatedBy;
		this.storeName = storeName;
		this.storePhoneNumber = storePhoneNumber;
		this.storeEmail = storeEmail;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.deleteFlag = deleteFlag;
	}

	public Store2doorStores(long id, User userByCreatedBy, User userByLastUpdatedBy, String storeName,
			String storeLocation, String storePhoneNumber, String storeEmail, Date createdTime, Date lastUpdatedTime,
			char deleteFlag, Set<StoreAdmin> storeAdmins, Set<UserCart> userCarts,Set<StoresImages> storesImageses, Set<StoreCategories> storeCategorieses,Set<UserOrdersHistory> userOrdersHistories, Set<UserOrders> userOrderses) {
		this.id = id;
		this.userByCreatedBy = userByCreatedBy;
		this.userByLastUpdatedBy = userByLastUpdatedBy;
		this.storeName = storeName;
		this.storeLocation = storeLocation;
		this.storePhoneNumber = storePhoneNumber;
		this.storeEmail = storeEmail;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.deleteFlag = deleteFlag;
		this.storeAdmins = storeAdmins;
		this.storeCategorieses = storeCategorieses;
		this.storeImages = storesImageses;
		this.userOrdersHistories = userOrdersHistories;
		this.userOrderses = userOrderses;
		this.userCarts = userCarts;
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
	@JoinColumn(name = "created_by")
	public User getUserByCreatedBy() {
		return this.userByCreatedBy;
	}

	public void setUserByCreatedBy(User userByCreatedBy) {
		this.userByCreatedBy = userByCreatedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "last_updated_by", nullable = false)
	public User getUserByLastUpdatedBy() {
		return this.userByLastUpdatedBy;
	}

	public void setUserByLastUpdatedBy(User userByLastUpdatedBy) {
		this.userByLastUpdatedBy = userByLastUpdatedBy;
	}

	@Column(name = "store_name", nullable = false, length = 100)
	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name = "store_location", length = 300)
	public String getStoreLocation() {
		return this.storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	@Column(name = "store_phone_number", nullable = false, length = 10)
	public String getStorePhoneNumber() {
		return this.storePhoneNumber;
	}

	public void setStorePhoneNumber(String storePhoneNumber) {
		this.storePhoneNumber = storePhoneNumber;
	}

	@Column(name = "store_email", nullable = false, length = 100)
	public String getStoreEmail() {
		return this.storeEmail;
	}

	public void setStoreEmail(String storeEmail) {
		this.storeEmail = storeEmail;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store2doorStores",cascade=CascadeType.ALL)
	public Set<StoreAdmin> getStoreAdmins() {
		return this.storeAdmins;
	}

	public void setStoreAdmins(Set<StoreAdmin> storeAdmins) {
		this.storeAdmins = storeAdmins;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store2doorStores",cascade=CascadeType.ALL)
	public Set<StoreCategories> getStoreCategorieses() {
		return this.storeCategorieses;
	}

	public void setStoreCategorieses(Set<StoreCategories> storeCategorieses) {
		this.storeCategorieses = storeCategorieses;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "store2doorStores",cascade =CascadeType.ALL)
	public Set<StoresImages> getStoresImageses() {
		return this.storeImages;
	}

	public void setStoresImageses(Set<StoresImages> storesImageses) {
		this.storeImages = storesImageses;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store2doorStores",cascade =CascadeType.ALL)
	public Set<UserOrdersHistory> getUserOrdersHistories() {
		return this.userOrdersHistories;
	}

	public void setUserOrdersHistories(Set<UserOrdersHistory> userOrdersHistories) {
		this.userOrdersHistories = userOrdersHistories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store2doorStores",cascade =CascadeType.ALL)
	public Set<UserOrders> getUserOrderses() {
		return this.userOrderses;
	}

	public void setUserOrderses(Set<UserOrders> userOrderses) {
		this.userOrderses = userOrderses;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store2doorStores")
	public Set<UserCart> getUserCarts() {
		return this.userCarts;
	}

	public void setUserCarts(Set<UserCart> userCarts) {
		this.userCarts = userCarts;
	}

}
