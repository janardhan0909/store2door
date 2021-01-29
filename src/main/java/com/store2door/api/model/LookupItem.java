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

/**
 * @author Mahesh Yadav
 */
@Entity
@Table(name = "lookup_item", catalog = "store2door")
public class LookupItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private LookupCategory lookupCategory;
	private User UserByCreatedBy;
	private User UserByLastUpdatedBy;
	private String itemKey;
	private String itemValue;
	private short displayOrder;
	private char activeFlag;
	private Date createdTime;
	private Date lastUpdatedTime;

	public LookupItem() {
	}

	public LookupItem(long id, LookupCategory lookupCategory, User UserByCreatedBy, User UserByLastUpdatedBy,
			String itemKey, String itemValue, short displayOrder, char activeFlag, Date createdTime,
			Date lastUpdatedTime) {
		this.id = id;
		this.lookupCategory = lookupCategory;
		this.UserByCreatedBy = UserByCreatedBy;
		this.UserByLastUpdatedBy = UserByLastUpdatedBy;
		this.itemKey = itemKey;
		this.itemValue = itemValue;
		this.displayOrder = displayOrder;
		this.activeFlag = activeFlag;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
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
	public LookupCategory getLookupCategory() {
		return this.lookupCategory;
	}

	public void setLookupCategory(LookupCategory lookupCategory) {
		this.lookupCategory = lookupCategory;
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

	@Column(name = "item_key", nullable = false, length = 50)
	public String getItemKey() {
		return this.itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	@Column(name = "item_value", nullable = false, length = 50)
	public String getItemValue() {
		return this.itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	@Column(name = "display_order", nullable = false)
	public short getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(short displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Column(name = "active_flag", nullable = false, length = 1)
	public char getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(char activeFlag) {
		this.activeFlag = activeFlag;
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
