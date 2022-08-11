package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Payment;
import com.lawencon.community.model.PremiumPaymentHistory;
import com.lawencon.community.model.PremiumType;
import com.lawencon.community.model.User;

@Repository
public class PremiumPaymentHistoryDao extends AbstractJpaDao<PremiumPaymentHistory> {
	
	public List<PremiumPaymentHistory> getByUser(String userId) throws Exception {
		String sql = "SELECT * FROM premium_payment_history WHERE user_id = :id";
		
		List<?> result = createNativeQuery(sql)
				.setParameter("id", userId)
				.getResultList();
		
		List<PremiumPaymentHistory> listHistory = new ArrayList<>();
		
		result.forEach(data -> {
			Object[] objArr = (Object[]) data;
			PremiumPaymentHistory premium = new PremiumPaymentHistory();
			premium.setId(objArr[0].toString());
			premium.setPremiumPaymentHistoryCode(objArr[1].toString());
			
			Payment payment = new Payment();
			payment.setId(objArr[2].toString());
			premium.setPayment(payment);
			
			User user = new User();
			user.setId(objArr[3].toString());
			premium.setUser(user);
			
			PremiumType premiumType = new PremiumType();
			premiumType.setId(objArr[4].toString());
			premium.setPremiumType(premiumType);
			
			premium.setTrxNo(objArr[5].toString());
			premium.setCreatedAt(((Timestamp)objArr[6]).toLocalDateTime());
			premium.setCreatedBy(objArr[7].toString());
			
			if(objArr[8] != null) {
				premium.setUpdatedAt(((Timestamp)objArr[8]).toLocalDateTime());
			}
			
			if(objArr[9] != null) {
				premium.setUpdatedBy(objArr[9].toString());
			}
			premium.setIsActive(Boolean.valueOf(objArr[10].toString()));
			premium.setVersion(Integer.valueOf(objArr[11].toString()));
			
			listHistory.add(premium);
		});
		
		return listHistory;
	}
	
	public PremiumPaymentHistory getPremium(String userId) throws Exception {
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
				payment.setId(objArr[2].toString());
				premium.setPayment(payment);
				
				User user = new User();
				user.setId(objArr[3].toString());
				premium.setUser(user);
				
				PremiumType premiumType = new PremiumType();
				premiumType.setId(objArr[4].toString());
				premium.setPremiumType(premiumType);
				
				premium.setTrxNo(objArr[5].toString());
				premium.setCreatedAt(((Timestamp)objArr[6]).toLocalDateTime());
				premium.setCreatedBy(objArr[7].toString());
				
				if(objArr[8] != null) {
					premium.setUpdatedAt(((Timestamp)objArr[8]).toLocalDateTime());
				}
				
				if(objArr[9] != null) {
					premium.setUpdatedBy(objArr[9].toString());
				}
				premium.setIsActive(Boolean.valueOf(objArr[10].toString()));
				premium.setVersion(Integer.valueOf(objArr[11].toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return premium;
	}
	
	public PremiumPaymentHistory getByPayment(String id) throws Exception {
		String sql = "SELECT * FROM premium_payment_history WHERE payment_id= :id";
		PremiumPaymentHistory premium = null;
		try {
			Object result = createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			
			if(result != null) {
				Object[] objArr = (Object[]) result;
				premium = new PremiumPaymentHistory();
				premium.setId(objArr[0].toString());
				premium.setPremiumPaymentHistoryCode(objArr[1].toString());
				
				Payment payment = new Payment();
				payment.setId(objArr[2].toString());
				premium.setPayment(payment);
				
				User user = new User();
				user.setId(objArr[3].toString());
				premium.setUser(user);
				
				PremiumType premiumType = new PremiumType();
				premiumType.setId(objArr[4].toString());
				premium.setPremiumType(premiumType);
				
				premium.setTrxNo(objArr[5].toString());
				premium.setCreatedAt(((Timestamp)objArr[6]).toLocalDateTime());
				premium.setCreatedBy(objArr[7].toString());
				
				if(objArr[8] != null) {
					premium.setUpdatedAt(((Timestamp)objArr[8]).toLocalDateTime());
				}
				
				if(objArr[9] != null) {
					premium.setUpdatedBy(objArr[9].toString());
				}
				premium.setIsActive(Boolean.valueOf(objArr[10].toString()));
				premium.setVersion(Integer.valueOf(objArr[11].toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return premium;
	}
	
	
	public Long countAllPremiumUser() throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT COUNT(id) FROM premium_payment_history ")
		.append(" WHERE is_active = true ");
		
		Long total = 0L;
		try {
			Object result = createNativeQuery(sqlBuilder.toString()).getSingleResult();
			if( result != null) {
				 total = Long.valueOf(result.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}

}
