package com.store2door.api.dto;

import java.util.List;

public class CategoryItemsDTO {
	private long id;
	private String itemName;
	private String itemDescription;
	private double itemPrice;
	private double itemQuantity;
	private char deleteFlag;
	private long storeId;
	private long categoryId;
	private List<String> itemsImages;
	private String unitofMesure;
	private double marketPrice;
	private int displayOrder;
	private List<CategoryItemRatingDTO> itemRatingDto;
	
	public  CategoryItemsDTO() {
		
	}

	public CategoryItemsDTO(long id, String itemName, String itemDescription, double itemPrice, double itemQuantity,
			List<String> itemsImages) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemsImages = itemsImages;
		
	}


	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public double getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(double itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public char getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(char deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public List<String> getItemsImages() {
		return itemsImages;
	}
	public void setItemsImages(List<String> itemsImages) {
		this.itemsImages = itemsImages;
	}

	public CategoryItemsDTO(long id, String itemName) {
		super();
		this.id = id;
		this.itemName = itemName;
	}

	public String getUnitofMesure() {
		return unitofMesure;
	}

	public void setUnitofMesure(String unitofMesure) {
		this.unitofMesure = unitofMesure;
	}

	public List<CategoryItemRatingDTO> getItemRatingDto() {
		return itemRatingDto;
	}

	public void setItemRatingDto(List<CategoryItemRatingDTO> itemRatingDto) {
		this.itemRatingDto = itemRatingDto;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
}
