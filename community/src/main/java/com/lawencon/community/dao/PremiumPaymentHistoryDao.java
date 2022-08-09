package com.lawencon.community.dao;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Payment;
import com.lawencon.community.model.PremiumPaymentHistory;
import com.lawencon.community.model.PremiumType;
import com.lawencon.community.model.User;

@Repository
public class PremiumPaymentHistoryDao extends AbstractJpaDao<PremiumPaymentHistory> {
	
	public PremiumPaymentHistory getByUser(String userId) throws Exception {
		String sql = "SELECT * FROM premium_payment_history WHERE user_id = :id AND is_active = true";
		PremiumPaymentHistory premium = null;
		try {
			Object result = createNativeQuery(sql)
					.setParameter("id", userId)
					.getSingleResult();
			if(result != null) {
				Object[] objArr = (Object[]) result;
				premium = new PremiumPaymentHistory();
				premium.setId(objArr[0].toString());
				premium.setPremiumPaymentHistoryCode(objArr[1].toString());
				
				Payment payment = new Payment();
				payment.setId(objArr[11].toString());
				premium.setPayment(payment);
				
				User user = new User();
				user.setId(objArr[2].toString());
				premium.setUser(user);
				
				PremiumType premiumType = new PremiumType();
				premiumType.setId(objArr[3].toString());
				premium.setPremiumType(premiumType);
				
				premium.setTrxNo(objArr[4].toString());
				premium.setCreatedAt(((Timestamp)objArr[5]).toLocalDateTime());
				premium.setCreatedBy(objArr[6].toString());
				
				if(objArr[7] != null) {
					premium.setUpdatedAt(((Timestamp)objArr[7]).toLocalDateTime());
				}
				
				if(objArr[8] != null) {
					premium.setUpdatedBy(objArr[8].toString());
				}
				premium.setIsActive(Boolean.valueOf(objArr[9].toString()));
				premium.setVersion(Integer.valueOf(objArr[10].toString()));
			} 
		} catch (Exception NoResultException ) {
			return premium;
		}
		
		return premium;
	}

}
