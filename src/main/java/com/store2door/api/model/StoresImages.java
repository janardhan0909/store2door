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
@Table(name = "stores_images", catalog = "store2door")
public class StoresImages {

	private long id;
	private Store2doorStores store2doorStores;
	private String image;

	public StoresImages() {
	}

	public StoresImages(long id, Store2doorStores store2doorStores) {
		this.id = id;
		this.store2doorStores = store2doorStores;
	}

	public StoresImages(long id, Store2doorStores store2doorStores, String image) {
		this.id = id;
		this.store2doorStores = store2doorStores;
		this.image = image;
	}
	public StoresImages(Store2doorStores store2doorStores, String image) {
		this.store2doorStores = store2doorStores;
		this.image = image;
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

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
