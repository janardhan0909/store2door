package com.store2door.api.dto;

import java.util.List;

public class StoreCategoriesDTO {
	private long id;
	private String categoryName;
	private String categoryDescription;
	private char deleteFlag;
	private long storeId;
	private List<String> categoryImages;
	
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public char getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(char deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public List<String> getCategoryImages() {
		return categoryImages;
	}
	public void setCategoryImages(List<String> categoryImages) {
		this.categoryImages = categoryImages;
	}
	
	

}
