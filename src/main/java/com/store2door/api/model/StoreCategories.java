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
 * @author Mahesh Yadav
 */
@Entity
@Table(name = "store_categories", catalog = "store2door")
public class StoreCategories implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private Store2doorStores store2doorStores;
	private User UserByCreatedBy;
	private User UserByLastUpdatedBy;
	private String categoryName;
	private String categoryDescription;
	private Date createdTime;
	private Date lastUpdatedTime;
	private char deleteFlag;
	private Set<CategoryItems> categoryItemses = new HashSet<CategoryItems>(0);
	private Set<StoreCategoryImages> storeCategoryImageses = new HashSet<StoreCategoryImages>(0);

	public StoreCategories() {
	}

		public StoreCategories(long id) {
		this.id = id;
	}
	public StoreCategories(long id, Store2doorStores store2doorStores, User UserByCreatedBy, User UserByLastUpdatedBy,
			String categoryName, Date createdTime, Date lastUpdatedTime, char deleteFlag) {
		this.id = id;
		this.store2doorStores = store2doorStores;
		this.UserByCreatedBy = UserByCreatedBy;
		this.UserByLastUpdatedBy = UserByLastUpdatedBy;
		this.categoryName = categoryName;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.deleteFlag = deleteFlag;
	}

	public StoreCategories(long id, Store2doorStores store2doorStores, User UserByCreatedBy, User UserByLastUpdatedBy,
			String categoryName, String categoryDescription, Date createdTime, Date lastUpdatedTime, char deleteFlag,
			Set<CategoryItems> categoryItemses,Set<StoreCategoryImages> storeCategoryImageses) {
		this.id = id;
		this.store2doorStores = store2doorStores;
		this.UserByCreatedBy = UserByCreatedBy;
		this.UserByLastUpdatedBy = UserByLastUpdatedBy;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.deleteFlag = deleteFlag;
		this.categoryItemses = categoryItemses;
		this.storeCategoryImageses = storeCategoryImageses;
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
	@JoinColumn(name = "store_id", nullable = false)
	public Store2doorStores getstore2doorStores() {
		return this.store2doorStores;
	}

	public void setstore2doorStores(Store2doorStores store2doorStores) {
		this.store2doorStores = store2doorStores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	public User getUserByCreatedBy() {
		return this.UserByCreatedBy;
	}

	public void setUserByCreatedBy(User UserByCreatedBy) {
		this.UserByCreatedBy = UserByCreatedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "last_updated_by", nullable = false)
	public User getUserByLastUpdatedBy() {
		return this.UserByLastUpdatedBy;
	}

	public void setUserByLastUpdatedBy(User UserByLastUpdatedBy) {
		this.UserByLastUpdatedBy = UserByLastUpdatedBy;
	}

	@Column(name = "category_name", nullable = false, length = 50)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "category_description", length = 200)
	public String getCategoryDescription() {
		return this.categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeCategories",cascade=CascadeType.ALL)
	public Set<CategoryItems> getCategoryItemses() {
		return this.categoryItemses;
	}

	public void setCategoryItemses(Set<CategoryItems> categoryItemses) {
		this.categoryItemses = categoryItemses;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeCategories",cascade=CascadeType.ALL)
	public Set<StoreCategoryImages> getStoreCategoryImageses() {
		return this.storeCategoryImageses;
	}

	public void setStoreCategoryImageses(Set<StoreCategoryImages> storeCategoryImageses) {
		this.storeCategoryImageses = storeCategoryImageses;
	}

}
