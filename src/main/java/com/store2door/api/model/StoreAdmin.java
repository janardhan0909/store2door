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
 * @author Mahesh Yadav
 */
@Entity
@Table(name = "store_admin", catalog = "store2door")
public class StoreAdmin implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Store2doorStores store2doorStores;
	private User user;

	public StoreAdmin() {
	}

	public StoreAdmin(long id, Store2doorStores store2doorStores, User user) {
		this.id = id;
		this.store2doorStores = store2doorStores;
		this.user = user;
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
	@JoinColumn(name = "store_admin_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
