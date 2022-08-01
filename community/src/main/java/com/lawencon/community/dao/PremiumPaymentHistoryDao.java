package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.PremiumPaymentHistory;

@Repository
public class PremiumPaymentHistoryDao extends AbstractJpaDao<PremiumPaymentHistory> {
	
	public PremiumPaymentHistory getByUser(String userId) throws Exception {
		String sql = "SELECT * FROM premium_payment_history WHERE user_id = :id";
		return null;
	}

}
