package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.HistoryPayment;
import com.lawencon.community.model.User;

@Repository
public class HistoryPaymentDao extends AbstractJpaDao<HistoryPayment>{
	
	public List<HistoryPayment> findAllByUserId(String userId) throws Exception{
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT hp.id, hp.historyPaymentCode, hp.user, hp.trxNo FROM history_payment hp ")
					.append("INNER JOIN users u ON u.id = hp.user ")
					.append("WHERE hp.user =:userId");
		
		
		List<?> result = createNativeQuery(sqlBuilder.toString())
						.setParameter("userId", userId)
						.getResultList();
		
		List<HistoryPayment> historyPayments = new ArrayList<HistoryPayment>();
		
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			HistoryPayment historyPayment = new HistoryPayment();
			historyPayment.setId(objArr[0].toString());
			historyPayment.setHistoryPaymentCode(objArr[1].toString());
			
			User user = new User();
			user.setId(objArr[2].toString());
			
			historyPayment.setUser(user);
			historyPayment.setTrxNo(objArr[3].toString());
			
			historyPayment.setCreatedBy(objArr[4].toString());
			if(objArr[5] != null) {
				historyPayment.setCreatedAt(((Timestamp) objArr[5]).toLocalDateTime());
			}
			
			historyPayment.setUpdatedBy(objArr[6].toString());
			if (objArr[7] != null) {
				historyPayment.setUpdatedAt(((Timestamp) objArr[7]).toLocalDateTime());
			}
			
			historyPayment.setIsActive(Boolean.valueOf(objArr[8].toString()));
			historyPayment.setVersion(Integer.valueOf(objArr[9].toString()));
			
			historyPayments.add(historyPayment);
		
		});
		
		return historyPayments;
		
	}
	
}
