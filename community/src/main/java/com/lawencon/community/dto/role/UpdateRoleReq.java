package com.lawencon.community.dto.role;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateRoleReq {

	@NotNull(message = "ID can't be empty")
	@Min(value = 1, message = "Minimum ID must be greater than 0")
	private Long id;

	@NotBlank(message = "Role Name can't be empty")
	@Size(min = 3, max = 50, message = "Role Name size must be between 3 to 50 ")
	private String roleName;

	@NotBlank(message = "Role Code can't be empty")
	@Size(min = 3, max = 50, message = "Role Code size must be between 3 to 50")
	private String roleCode;
	
	@NotNull(message = "Active status can't be empty")
	private Boolean isActive;

	@NotNull(message = "Version can't be empty")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
	

}
