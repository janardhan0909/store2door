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
@Table(name = "store_category_item_images", catalog = "store2door")
public class StoreCategoryItemImages {


	private long id;
	private CategoryItems categoryItems;
	private String itemImage;

	public StoreCategoryItemImages() {
	}

	public StoreCategoryItemImages(long id, CategoryItems categoryItems, String itemImage) {
		this.id = id;
		this.categoryItems = categoryItems;
		this.itemImage = itemImage;
	}
	public StoreCategoryItemImages(CategoryItems categoryItems, String itemImage) {
		this.categoryItems = categoryItems;
		this.itemImage = itemImage;
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
	@JoinColumn(name = "category_item_id", nullable = false)
	public CategoryItems getCategoryItems() {
		return this.categoryItems;
	}

	public void setCategoryItems(CategoryItems categoryItems) {
		this.categoryItems = categoryItems;
	}

	@Column(name = "item_image", nullable = false)
	public String getItemImage() {
		return this.itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
}
