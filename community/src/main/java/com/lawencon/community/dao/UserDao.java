package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;

@Repository
public class UserDao extends AbstractJpaDao<User> {

	public User findByRoleCode(String roleCode) throws Exception {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT u.id, u.email, u.role_id FROM users u ")
				.append(" INNER JOIN roles r ON u.role_id = r.id ")
				.append(" WHERE r.role_code = :roleCode ");
		User user = null;
		try {
			Object result = createNativeQuery(sqlBuilder.toString())
					.setParameter("roleCode", roleCode)
					.getSingleResult();
			if (result != null) {
				Object[] objArr = (Object[]) result;
				user = new User();
				user.setId(objArr[0].toString());
				user.setEmail(objArr[1].toString());
				Role role = new Role();
				role.setId(objArr[2].toString());
				user.setRole(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findByEmail(String email) throws Exception {
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT u.id, u.email, r.id, r.role_code ")
		.append(" FROM users INNER JOIN u.role_id = r.id ")
		.append(" WHERE email = :email ");
		User user = null;
		try {
			Object result = createNativeQuery(sqlBuilder.toString()).setParameter("email", email).getSingleResult();
			if (result != null) {
				Object[] objArr = (Object[]) result;
				user = new User();
				user.setId(objArr[0].toString());
				user.setEmail(objArr[1].toString());

				Role role = new Role();
				role.setId(objArr[2].toString());
				role.setRoleCode(objArr[3].toString());

				user.setRole(role);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}