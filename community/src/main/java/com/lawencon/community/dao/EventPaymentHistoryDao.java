package com.lawencon.community.dao;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.EventHeader;
import com.lawencon.community.model.EventPaymentHistory;
import com.lawencon.community.model.Payment;
import com.lawencon.community.model.User;

@Repository
public class EventPaymentHistoryDao extends AbstractJpaDao<EventPaymentHistory> {
	
	public EventPaymentHistory getByPayment(String id) throws Exception {
		String sql = "SELECT * FROM event_payment_history WHERE payment_id = :id";
		
		EventPaymentHistory eventPayment = null;
		try {
			Object result = createNativeQuery(sql).setParameter("id", id)
					.getSingleResult();
			if(result != null) {
				Object[] objArr = (Object[]) result;
				eventPayment = new EventPaymentHistory();
				eventPayment.setId(objArr[0].toString());
				eventPayment.setEvetnPaymentCode(objArr[1].toString());
				
				Payment payment = new Payment();
				payment.setId(objArr[2].toString());
				eventPayment.setPayment(payment);
				
				User user = new User();
				user.setId(objArr[3].toString());
				
				EventHeader eventHeader = new EventHeader();
				eventHeader.setId(objArr[4].toString());
				
				eventPayment.setTrxNo(objArr[5].toString());
				eventPayment.setCreatedAt(((Timestamp)objArr[6]).toLocalDateTime());
				eventPayment.setCreatedBy(objArr[7].toString());
				
				if(objArr[8] != null) {
					eventPayment.setUpdatedAt(((Timestamp)objArr[8]).toLocalDateTime());
				}
				
				if(objArr[9] != null) {
					eventPayment.setUpdatedBy(objArr[9].toString());
				}
				eventPayment.setIsActive(Boolean.valueOf(objArr[10].toString()));
				eventPayment.setVersion(Integer.valueOf(objArr[11].toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return eventPayment;
	}
	

}
