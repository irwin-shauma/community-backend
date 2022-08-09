package com.lawencon.community.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Balance;
import com.lawencon.community.model.User;

@Repository
public class BalanceDao extends AbstractJpaDao<Balance> {
	public Balance findByUserId(String userId) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT b.id, b.balance_code, b.current_balance, b.user_id FROM balance b ")
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}

	public Balance findSystem() throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT b.id, b.balance_code, b.current_balance, b.user_id, r.role_code FROM balance b ")
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}
}
