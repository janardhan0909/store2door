package com.store2door.api.dto;

public class LookUpItemDTO{

	private Long id;
	private Long categoryId;
	private String key;
	private String value;
	private String displayOrder;
	private String activeFlag;
	private String createdTime;
	private String lastUpdatedTime;
	private long createdBy;
	
	
	
	public LookUpItemDTO(Long id, String key, String value, String displayOrder,
			long createdBy) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
		this.displayOrder = displayOrder;
		this.createdBy = createdBy;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	
	
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	

}
