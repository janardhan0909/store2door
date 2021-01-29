package com.store2door.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
* @author Janardhan
*/
@Entity
@Table(name = "store_category_images", catalog = "store2door")
public class StoreCategoryImages {

	private long id;
	private StoreCategories storeCategories;
	private String categoryImage;

	public StoreCategoryImages() {
	}

	public StoreCategoryImages(long id, StoreCategories storeCategories, String categoryImage) {
		this.id = id;
		this.storeCategories = storeCategories;
		this.categoryImage = categoryImage;
	}
	
	public StoreCategoryImages(StoreCategories storeCategories, String categoryImage) {
		this.storeCategories = storeCategories;
		this.categoryImage = categoryImage;
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

	@Column(name = "category_image", nullable = false)
	public String getCategoryImage() {
		return this.categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

}
