package com.store2door.api.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.UniqueConstraint;

/**
 * @author Mahesh Yadav
 */
@Entity
@Table(name = "lookup_category", catalog = "store2door", uniqueConstraints = @UniqueConstraint(columnNames = "category_name"))
public class LookupCategory implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private User UserByCreatedBy;
	private User UserByLastUpdatedBy;
	private String categoryName;
	private String categoryDescription;
	private Date createdTime;
	private Date lastUpdatedTime;
	private Set<LookupItem> lookupItems = new HashSet<LookupItem>(0);

	public LookupCategory() {
	}

	public LookupCategory(long id, User UserByCreatedBy, User UserByLastUpdatedBy, String categoryName,
			String categoryDescription, Date createdTime, Date lastUpdatedTime) {
		this.id = id;
		this.UserByCreatedBy = UserByCreatedBy;
		this.UserByLastUpdatedBy = UserByLastUpdatedBy;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public LookupCategory(long id, User UserByCreatedBy, User UserByLastUpdatedBy, String categoryName,
			String categoryDescription, Date createdTime, Date lastUpdatedTime, Set<LookupItem> lookupItems) {
		this.id = id;
		this.UserByCreatedBy = UserByCreatedBy;
		this.UserByLastUpdatedBy = UserByLastUpdatedBy;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.lookupItems = lookupItems;
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

	@Column(name = "category_name", unique = true, nullable = false, length = 50)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "category_description", nullable = false, length = 100)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lookupCategory")
	public Set<LookupItem> getLookupItems() {
		return this.lookupItems;
	}

	public void setLookupItems(Set<LookupItem> lookupItems) {
		this.lookupItems = lookupItems;
	}

}
