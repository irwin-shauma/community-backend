package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.PremiumPaymentHistory;
import com.lawencon.community.model.User;

@Repository
public class PremiumPaymentHistoryDao extends AbstractJpaDao<PremiumPaymentHistory> {
	
	public PremiumPaymentHistory getByUser(String userId) throws Exception {
		String sql = "SELECT * FROM premium_payment_history WHERE user_id = :id";
		PremiumPaymentHistory premium = null;
		try {
			Object result = createNativeQuery(sql)
					.setParameter("id", userId)
					.getSingleResult();
			if(result != null) {
				Object[] objArr = (Object[]) result;
				premium = new PremiumPaymentHistory();
				premium.setId(objArr[0].toString());
				
				User user = new User();
				user.setId(objArr[2].toString());
				premium.setUser(user);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return premium;
	}

}
