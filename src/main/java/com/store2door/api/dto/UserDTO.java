package com.store2door.api.dto;

import com.store2door.api.model.RoleName;

public class UserDTO {
	/**
	 * @author Mahesh Yadav
	 */

	private Long id;
	private String name;
	private String username;
	private String email;
	private long mobileNumber;
	private String userImage;
	private char deleteFlag;
	private RoleDTO role = new RoleDTO();
	private boolean isSuperAdmin;
	private boolean isAdmin;
	private boolean isUser;
	private String password;
    private String newPassword;

	public UserDTO() {
	}

	public UserDTO(RoleDTO role) {
		this.role = role;
		this.isSuperAdmin = String.valueOf(RoleName.ROLE_SUPER_ADMIN.name()).equals(this.role.getName().name());
		this.isAdmin = String.valueOf(RoleName.ROLE_ADMIN.name()).equals(this.role.getName().name());

	}
	public UserDTO(Long id, String name, String username, String email, long mobileNumber, String userImage,
			char deleteFlag, RoleDTO role, boolean isSuperAdmin, boolean isAdmin, boolean isUser) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.userImage = userImage;
		this.deleteFlag = deleteFlag;
		this.role = role;
		this.isSuperAdmin = isSuperAdmin;
		this.isAdmin = isAdmin;
		this.isUser = isUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public char getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(char deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
		this.isSuperAdmin = String.valueOf(RoleName.ROLE_SUPER_ADMIN.name()).equals(this.role.getName().name());
		this.isAdmin = String.valueOf(RoleName.ROLE_ADMIN.name()).equals(this.role.getName().name());
		this.isUser = String.valueOf(RoleName.ROLE_USER.name()).equals(this.role.getName().name());
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isUser() {
		return isUser;
	}

	public void setIsUser(boolean isUser) {
		this.isUser = isUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
