package com.store2door.api.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User{
    /**
	 * @author Mahesh Yadav
	 */

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 40)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;
    @Column(name = "mobile_number")
    private long mobileNumber;
    @Column(name = "user_image")
	private String userImage;
    @Column(name = "device_id")
    private String deviceId;
    
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", nullable = false, length = 19)
	private Date createdTime;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_time", nullable = false, length = 19)
	private Date updatedTime;
    @Column(name = "delete_flag", nullable = false, length = 1)
	private char deleteFlag;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedBy")
	private Set<Store2doorStores> store2doorStoresesForCreatedBy = new HashSet<Store2doorStores>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade=CascadeType.ALL)
	private Set<StoreAdmin> storeAdmins = new HashSet<StoreAdmin>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedBy",cascade=CascadeType.ALL)
	private Set<StoreCategories> storeCategoriesesForCreatedBy = new HashSet<StoreCategories>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByLastUpdatedBy",cascade=CascadeType.ALL)
    private Set<CategoryItems> categoryItemsesForLastUpdatedBy = new HashSet<CategoryItems>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedBy",cascade=CascadeType.ALL)
    private Set<CategoryItems> categoryItemsesForCreatedBy = new HashSet<CategoryItems>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedBy")
    private Set<LookupCategory> lookupCategoriesForCreatedBy = new HashSet<LookupCategory>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByLastUpdatedBy")
    private Set<Store2doorStores> store2doorStoresesForLastUpdatedBy = new HashSet<Store2doorStores>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByLastUpdatedBy",cascade=CascadeType.ALL)
    private Set<StoreCategories> storeCategoriesesForLastUpdatedBy = new HashSet<StoreCategories>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByLastUpdatedBy")
    private Set<LookupCategory> lookupCategoriesForLastUpdatedBy = new HashSet<LookupCategory>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByCreatedBy")
    private Set<LookupItem> lookupItemsForCreatedBy = new HashSet<LookupItem>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByLastUpdatedBy")
    private Set<LookupItem> lookupItemsForLastUpdatedBy = new HashSet<LookupItem>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade=CascadeType.ALL)
    private Set<UserOrders> userOrderses = new HashSet<UserOrders>(0);
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Set<UserOrders> getUserOrderses() {
		return userOrderses;
	}

	public void setUserOrderses(Set<UserOrders> userOrderses) {
		this.userOrderses = userOrderses;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public char getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(char deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Set<Store2doorStores> getstore2doorStoresesForCreatedBy() {
		return store2doorStoresesForCreatedBy;
	}

	public void setstore2doorStoresesForCreatedBy(Set<Store2doorStores> store2doorStoresesForCreatedBy) {
		this.store2doorStoresesForCreatedBy = store2doorStoresesForCreatedBy;
	}

	public Set<StoreAdmin> getStoreAdmins() {
		return storeAdmins;
	}

	public void setStoreAdmins(Set<StoreAdmin> storeAdmins) {
		this.storeAdmins = storeAdmins;
	}

	public Set<StoreCategories> getStoreCategoriesesForCreatedBy() {
		return storeCategoriesesForCreatedBy;
	}

	public void setStoreCategoriesesForCreatedBy(Set<StoreCategories> storeCategoriesesForCreatedBy) {
		this.storeCategoriesesForCreatedBy = storeCategoriesesForCreatedBy;
	}

	public Set<CategoryItems> getCategoryItemsesForLastUpdatedBy() {
		return categoryItemsesForLastUpdatedBy;
	}

	public void setCategoryItemsesForLastUpdatedBy(Set<CategoryItems> categoryItemsesForLastUpdatedBy) {
		this.categoryItemsesForLastUpdatedBy = categoryItemsesForLastUpdatedBy;
	}

	public Set<CategoryItems> getCategoryItemsesForCreatedBy() {
		return categoryItemsesForCreatedBy;
	}

	public void setCategoryItemsesForCreatedBy(Set<CategoryItems> categoryItemsesForCreatedBy) {
		this.categoryItemsesForCreatedBy = categoryItemsesForCreatedBy;
	}

	public Set<LookupCategory> getLookupCategoriesForCreatedBy() {
		return lookupCategoriesForCreatedBy;
	}

	public void setLookupCategoriesForCreatedBy(Set<LookupCategory> lookupCategoriesForCreatedBy) {
		this.lookupCategoriesForCreatedBy = lookupCategoriesForCreatedBy;
	}

	public Set<Store2doorStores> getstore2doorStoresesForLastUpdatedBy() {
		return store2doorStoresesForLastUpdatedBy;
	}

	public void setstore2doorStoresesForLastUpdatedBy(Set<Store2doorStores> store2doorStoresesForLastUpdatedBy) {
		this.store2doorStoresesForLastUpdatedBy = store2doorStoresesForLastUpdatedBy;
	}

	public void setStoreCategoriesesForLastUpdatedBy(Set<StoreCategories> storeCategoriesesForLastUpdatedBy) {
		this.storeCategoriesesForLastUpdatedBy = storeCategoriesesForLastUpdatedBy;
	}

	public Set<LookupCategory> getLookupCategoriesForLastUpdatedBy() {
		return lookupCategoriesForLastUpdatedBy;
	}

	public void setLookupCategoriesForLastUpdatedBy(Set<LookupCategory> lookupCategoriesForLastUpdatedBy) {
		this.lookupCategoriesForLastUpdatedBy = lookupCategoriesForLastUpdatedBy;
	}

	public Set<LookupItem> getLookupItemsForCreatedBy() {
		return lookupItemsForCreatedBy;
	}

	public void setLookupItemsForCreatedBy(Set<LookupItem> lookupItemsForCreatedBy) {
		this.lookupItemsForCreatedBy = lookupItemsForCreatedBy;
	}

	public Set<LookupItem> getLookupItemsForLastUpdatedBy() {
		return lookupItemsForLastUpdatedBy;
	}

	public void setLookupItemsForLastUpdatedBy(Set<LookupItem> lookupItemsForLastUpdatedBy) {
		this.lookupItemsForLastUpdatedBy = lookupItemsForLastUpdatedBy;
	}

    public User() {

    }

    public User(long id) {
      this.id = id;
    }

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String name, String username, String email, String password,long phoneNumber,Date createdTime,Date updatedTime,String userImage,char deleteFlag) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobileNumber = phoneNumber;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.userImage = userImage;
        this.deleteFlag = deleteFlag;
    }
    public User(String name, String username, String email, String password,Date createdTime,Date updatedTime,String userImage,char deleteFlag) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.userImage = userImage;
        this.deleteFlag = deleteFlag;
    }
    public User(String name, String username, String email, String password,Date createdTime,Date updatedTime,String userImage,char deleteFlag,String deviceId) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.userImage = userImage;
        this.deleteFlag = deleteFlag;
        this.deviceId=deviceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public Set<StoreCategories> getStoreCategoriesesForLastUpdatedBy() {
		return storeCategoriesesForLastUpdatedBy;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}