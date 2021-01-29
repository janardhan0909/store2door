package com.store2door.api.dto;

public class StoreAdminDTO {
    private long id;
    private long storeId;
    private long storeAdminId;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public long getStoreAdminId() {
		return storeAdminId;
	}
	public void setStoreAdminId(long storeAdminId) {
		this.storeAdminId = storeAdminId;
	}
}
