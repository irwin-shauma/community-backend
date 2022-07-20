package com.lawencon.community.dto.role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InsertRoleReq {
	
	@NotBlank(message = "Role Name can't be empty")
	@Size(min = 3, max = 50, message = "Role Name size must be between 3 to 50")
	private String roleName;
	
	@NotBlank(message = "Role Code can't be empty")
	@Size(min = 3, max = 50, message = "Role Code size must be between 3 to 50")
	private String roleCode;

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
}
