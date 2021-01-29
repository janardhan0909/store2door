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

@Entity
@Table(name = "user_address", catalog = "store2door")
public class UserAddress implements java.io.Serializable {

	/**
	 * @author Janardhan
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private User user;
	private String name;
	private String streetAddress;
	private String city;
	private String state;
	private String contactNo;
	private String pinCode;
	private String address;
	private Date createdTime;
	private String landMark;
	private Set<UserOrderItems> userOrderItemses = new HashSet<UserOrderItems>(0);
	private Set<UserOrderItemsHistory> userOrderItemsHistories = new HashSet<UserOrderItemsHistory>(0);

	public UserAddress() {
	}

	public UserAddress(User user, String name, String streetAddress, String city, String state, String contactNo,
			String pinCode, String address, Date createdTime,String landMark) {
		this.user = user;
		this.name = name;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.contactNo = contactNo;
		this.pinCode = pinCode;
		this.address = address;
		this.createdTime = createdTime;
		this.landMark=landMark;
	}
	public UserAddress(User user, String name, String streetAddress, String city, String state, String contactNo,
			String pinCode, String address, Date createdTime, Set<UserOrderItemsHistory> userOrderItemsHistories,Set<UserOrderItems> userOrderItemses) {
		this.user = user;
		this.name = name;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.contactNo = contactNo;
		this.pinCode = pinCode;
		this.address = address;
		this.createdTime = createdTime;
		this.userOrderItemsHistories = userOrderItemsHistories;
		this.userOrderItemses = userOrderItemses;
	}
	public UserAddress(long id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "street_address", nullable = false, length = 50)
	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	@Column(name = "city", nullable = false, length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state", nullable = false, length = 50)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "contact_no", nullable = false, length = 50)
	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Column(name = "pin_code", nullable = false, length = 50)
	public String getPinCode() {
		return this.pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Column(name = "address", nullable = false, length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", nullable = false, length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAddress")
	public Set<UserOrderItems> getUserOrderItemses() {
		return this.userOrderItemses;
	}

	public void setUserOrderItemses(Set<UserOrderItems> userOrderItemses) {
		this.userOrderItemses = userOrderItemses;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAddress")
	public Set<UserOrderItemsHistory> getUserOrderItemsHistories() {
		return this.userOrderItemsHistories;
	}

	public void setUserOrderItemsHistories(Set<UserOrderItemsHistory> userOrderItemsHistories) {
		this.userOrderItemsHistories = userOrderItemsHistories;
	}
	@Column(name = "land_mark")
	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	
}
