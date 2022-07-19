package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.lawencon.base.BaseEntity;

@Entity
@Indexed
@Table(uniqueConstraints = {
		@UniqueConstraint(
				name = "role_ck",
				columnNames = {"role_code"}
				)
})
public class Role extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;
	
	@FullTextField
	private String roleName;
	
	@FullTextField
	private String roleCode;
	
	
	
	@ManyToOne
	@Jo
	
	
	

}
