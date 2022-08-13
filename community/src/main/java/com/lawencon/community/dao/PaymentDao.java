package com.lawencon.community.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.dto.report.MemberRevenueReportData;
import com.lawencon.community.dto.report.MemberRevenueReportRes;
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
	
	public MemberRevenueReportRes getReportData(String id) throws Exception {
		
		List<MemberRevenueReportData> reports = new ArrayList<>();
		MemberRevenueReportRes response = new MemberRevenueReportRes();
		
		StringBuilder sqlBuilder = new StringBuilder()
				.append(" SELECT pf.full_name AS fullName, eph.trx_no AS trxNo,  ")
				.append(" eh.title AS title, et.type AS type, ")
				.append(" ed.price AS price, pm.created_at AS date ")
				.append(" FROM payment pm ")
				.append(" JOIN users u ON u.id = pm.user_id ")
				.append(" JOIN profile pf ON pf.id = u.profile_id ")
				.append(" JOIN event_payment_history eph ON eph.payment_id = pm.id ")
				.append(" JOIN event_header eh ON eh.id = eph.event_header_id ")
				.append(" JOIN event_type et ON et.id = eh.event_type_id ")
				.append(" JOIN event_detail ed ON ed.event_header_id = eh.id ")
				.append(" WHERE pm.user_id = :id");
		try {
			List<?> result = createNativeQuery(sqlBuilder.toString())
					.setParameter("id", id)
					.getResultList();
			
			if(result != null) {
				result.forEach(obj -> {
					Object[] objArr = (Object[]) obj;
					MemberRevenueReportData data = new MemberRevenueReportData();
					
					data.setFullName(objArr[0].toString());
					data.setTrxNo((objArr[1].toString()));
					data.setTitle(objArr[2].toString());
					data.setType(objArr[3].toString());
					data.setPrice(new BigDecimal( objArr[4].toString()));
					data.setDate(((Timestamp) objArr[5]).toLocalDateTime());
					reports.add(data);
				});
			}
			
			response.setData(reports);
			response.setCount(result.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return response;
	}
}
