package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "roles" , uniqueConstraints = {
		@UniqueConstraint(
				name = "role_bk",
				columnNames = {"role_code"}
				)
})
public class Role extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "role_code")
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
