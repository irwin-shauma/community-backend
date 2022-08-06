package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;
import com.lawencon.security.RefreshTokenEntity;

@Entity
@Table(name = "users", uniqueConstraints = { 
		@UniqueConstraint(
				name = "email_bk",
				columnNames =  "email" 
		),
		@UniqueConstraint(
				name = "user_code_bk",
				columnNames =  "user_code" 
		)
})
public class User extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "user_code")
	private String userCode;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@OneToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

	@Column(name = "email")
	private String email;

	@Column(name = "passwords")
	private String password;
	
	@OneToOne
	@JoinColumn(name = "token_id")
	private RefreshTokenEntity token;


	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
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

	public RefreshTokenEntity getToken() {
		return token;
	}

	public void setToken(RefreshTokenEntity token) {
		this.token = token;
	}

}
