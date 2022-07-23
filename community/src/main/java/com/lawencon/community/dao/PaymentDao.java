package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Payment;
import com.lawencon.community.model.User;

@Repository
public class PaymentDao extends AbstractJpaDao<Payment> {
	public Payment findByUserId(String userId) throws Exception{
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT p.id, p.paymentCode, p.fileId, p.userId  FROM payment p ")
					.append(" INNER JOIN file f ON f.id = p.fileId ")
					.append("INNER JOIN users u ON u.id = p.userId")
					.append("WHERE p.userId = :userId");
		
		Payment payment = null;
		try {
			Object result = createNativeQuery(sqlBuilder.toString())
					.setParameter("userId", userId)
					.getSingleResult();
			if(result != null) {
				Object[] objArr = (Object[]) result;
				payment = new Payment();
				payment.setId(objArr[0].toString());
				payment.setPaymentCode(objArr[1].toString());
				File file = new File();
				file.setId(objArr[2].toString());
				payment.setFile(file);
				User user = new User();
				user.setId(objArr[3].toString());
				payment.setUser(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return payment;
	}
}
