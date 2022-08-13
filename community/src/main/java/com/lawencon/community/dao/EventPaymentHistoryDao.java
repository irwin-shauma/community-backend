package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
				eventPayment.setEventPaymentCode(objArr[1].toString());
				
				Payment payment = new Payment();
				payment.setId(objArr[2].toString());
				eventPayment.setPayment(payment);
				
				User user = new User();
				user.setId(objArr[3].toString());
				eventPayment.setUser(user);
				
				EventHeader eventHeader = new EventHeader();
				eventHeader.setId(objArr[4].toString());
				eventPayment.setEventHeader(eventHeader);
				
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
	
	public List<EventPaymentHistory> findAllUnapprove(String query, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append("SELECT eph.* FROM event_payment_history eph INNER JOIN payment p ")
				.append("ON eph.payment_id = p.id WHERE p.is_approve = false");
		
		List<?> result = createNativeQuery(sql.toString()).getResultList();
		List<EventPaymentHistory> eventPayments = new ArrayList<>();
		
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			EventPaymentHistory eventPayment = new EventPaymentHistory();
			eventPayment.setId(objArr[0].toString());
			eventPayment.setEventPaymentCode(objArr[1].toString());
			
			Payment payment = new Payment();
			payment.setId(objArr[2].toString());
			eventPayment.setPayment(payment);
			
			User user = new User();
			user.setId(objArr[3].toString());
			eventPayment.setUser(user);
			
			EventHeader eventHeader = new EventHeader();
			eventHeader.setId(objArr[4].toString());
			eventPayment.setEventHeader(eventHeader);
			
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
			
			eventPayments.add(eventPayment);
		});
		
		return eventPayments;
		
	}
	
	
	public List<EventPaymentHistory> findAllByUser(String user,String query, Integer startPage, Integer maxPage) throws Exception {
		String sql = "SELECT * FROM event_payment_history WHERE user_id = :user";
		
		List<?> result = createNativeQuery(sql).setParameter("user", user).getResultList();
		List<EventPaymentHistory> eventPayments = new ArrayList<>();
		
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			EventPaymentHistory eventPayment = new EventPaymentHistory();
			eventPayment.setId(objArr[0].toString());
			eventPayment.setEventPaymentCode(objArr[1].toString());
			
			Payment payment = new Payment();
			payment.setId(objArr[2].toString());
			eventPayment.setPayment(payment);
			
			User users = new User();
			users.setId(objArr[3].toString());
			eventPayment.setUser(users);
			
			EventHeader eventHeader = new EventHeader();
			eventHeader.setId(objArr[4].toString());
			eventPayment.setEventHeader(eventHeader);
			
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
			
			eventPayments.add(eventPayment);
		});
		
		return eventPayments;
		
	}
	
	

}































