package com.lawencon.community.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Balance;
import com.lawencon.community.model.User;

@Repository
public class BalanceDao extends AbstractJpaDao<Balance> {
	public Balance findByUserId(String userId) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT b.id, b.balance_code, b.current_balance, b.user_id, b.created_at, b.created_by, b.version ")
				.append(" FROM balance b ")
				.append(" INNER JOIN users u ON u.id = b.user_id ")
				.append(" WHERE b.user_id = :userId ");

		Balance balance = null;
		try {
			Object result = createNativeQuery(sqlBuilder.toString()).setParameter("userId", userId).getSingleResult();

			if (result != null) {
				Object[] objArr = (Object[]) result;
				balance = new Balance();
				balance.setId(objArr[0].toString());
				balance.setBalanceCode(objArr[1].toString());
				balance.setCurrentBalance(new BigDecimal(objArr[2].toString()));

				User user = new User();
				user.setId(objArr[3].toString());
				balance.setUser(user);
				
				balance.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
				balance.setCreatedBy(objArr[5].toString());
				balance.setVersion(Integer.valueOf(objArr[6].toString()));			
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}

	public Balance findSystem() throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT b.id, b.balance_code, b.current_balance, b.user_id, r.role_code, b.created_at, b.created_by , b.version")
				.append(" FROM balance b ")
				.append(" INNER JOIN users u ON u.id = b.user_id ")
				.append(" INNER JOIN roles r ON r.id = u.role_id ")
				.append(" WHERE r.role_code = 'SYSTEM' ");

		Balance balance = null;
		try {
			Object result = createNativeQuery(sqlBuilder.toString()).getSingleResult();

			if (result != null) {
				Object[] objArr = (Object[]) result;
				balance = new Balance();
				balance.setId(objArr[0].toString());
				balance.setBalanceCode(objArr[1].toString());
				balance.setCurrentBalance(new BigDecimal(objArr[2].toString()));

				User user = new User();
				user.setId(objArr[3].toString());
				balance.setUser(user);
				
				balance.setCreatedAt(((Timestamp) objArr[5]).toLocalDateTime());
				balance.setCreatedBy(objArr[6].toString());
				balance.setVersion(Integer.valueOf(objArr[7].toString()));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}
	
	public BigDecimal findSystemBalance() throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT b.current_balance ")
				.append(" FROM balance b ")
				.append(" INNER JOIN users u ON u.id = b.user_id ")
				.append(" INNER JOIN roles r ON r.id = u.role_id ")
				.append(" WHERE r.role_code = 'SYSTEM' ");

		BigDecimal total = null;
		try {
			Object result = createNativeQuery(sqlBuilder.toString()).getSingleResult();

			if (result != null) {
				total = new BigDecimal(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
}
