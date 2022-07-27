package com.lawencon.community.dao;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Role;

@Repository
public class RoleDao extends AbstractJpaDao<Role> {

	public Role findByRoleMember() throws Exception {
		Role role = null;
		String sql = "SELECT * FROM roles WHERE role_code = 'MEMBER'";
		try {
			Object result = createNativeQuery(sql).getSingleResult();
			if (result != null) {
				role = new Role();
				Object[] objArr = (Object[]) result;
				role.setId(objArr[0].toString());
				role.setRoleCode(objArr[1].toString());
				role.setRoleName(objArr[2].toString());
				role.setCreatedBy(objArr[3].toString());
				role.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
				role.setIsActive(((Boolean) objArr[7]));
				role.setVersion(Integer.valueOf(objArr[8].toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
	
	public Role findByRoleSystem() throws Exception {
		Role role = null;
		String sql = "SELECT * FROM roles WHERE role_code = 'SYSTEM'";
		try {
			Object result = createNativeQuery(sql).getSingleResult();
			if (result != null) {
				role = new Role();
				Object[] objArr = (Object[]) result;
				role.setId(objArr[0].toString());
				role.setRoleCode(objArr[1].toString());
				role.setRoleName(objArr[2].toString());
				role.setCreatedBy(objArr[3].toString());
				role.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
				role.setIsActive(((Boolean) objArr[7]));
				role.setVersion(Integer.valueOf(objArr[8].toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

}
