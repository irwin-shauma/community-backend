package com.lawencon.community.dao;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Balance;
import com.lawencon.community.model.User;

public class BalanceDao extends AbstractJpaDao<Balance>{
	public Balance findByUserId(String userId) throws Exception{
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT b.id, b.balanceCode, b.currentBalance, b.user FROM balance b")
					.append("INNER JOIN users u ON u.id = b.user")
					.append("WHERE b.user = :userId");
		
		Balance balance =null;
		try {
			Object result = createNativeQuery(sqlBuilder.toString())
					.setParameter("userId", userId)
					.getSingleResult();
			
			if(result != null) {
				Object[] objArr = (Object[]) result;
				balance = new Balance();
				balance.setId(objArr[0].toString());
				balance.setBalanceCode(objArr[1].toString());
				balance.setCurrentBalance(Float.valueOf(objArr[2].toString()));
				
				User user = new User();
				user.setId(objArr[3].toString());
				balance.setUser(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}
}
