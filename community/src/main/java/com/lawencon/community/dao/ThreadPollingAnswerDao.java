package com.lawencon.community.dao;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadPollingAnswer;

@Repository
public class ThreadPollingAnswerDao extends AbstractJpaDao<ThreadPollingAnswer> {
	
	public BigInteger countAllAnswer(String id) {
		
		StringBuilder sql = new StringBuilder()
				.append("SELECT COUNT(tpa.id) FROM thread_polling_answer tpa ")
				.append("LEFT JOIN thread_polling_detail tpd on tpa.thread_detail_polling_id = tpd.id ")
				.append("LEFT JOIN thread_header_polling thp on tpd.thread_header_polling_id = thp.id ")
				.append("WHERE thp.id = :id");
		
		BigInteger count = (BigInteger) createNativeQuery(sql.toString())
				.setParameter("id", id).getSingleResult();
		
		return count;
	}
	
public BigInteger countAnswer(String id) {
		
		String sql = "SELECT count(id) FROM thread_polling_answer WHERE thread_detail_polling_id = :id";
		
		BigInteger count = (BigInteger) createNativeQuery(sql)
				.setParameter("id", id).getSingleResult();
		
		return count;
	}

}
