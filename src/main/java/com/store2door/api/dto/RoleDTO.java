package com.store2door.api.dto;

import com.store2door.api.model.RoleName;

public class RoleDTO {
	private long id;
	private RoleName name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}

	public RoleDTO() {

	}

	public RoleDTO(RoleName name,long id) {
		this.name = name;
		this.id = id;
	}

}
