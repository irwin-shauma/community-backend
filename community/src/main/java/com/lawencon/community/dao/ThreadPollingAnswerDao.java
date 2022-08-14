package com.lawencon.community.dao;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadPollingAnswer;

@Repository
public class ThreadPollingAnswerDao extends AbstractJpaDao<ThreadPollingAnswer> {

	public ThreadPollingAnswer findByThreadAndUser(String thread, String user) throws Exception {
		String sql = "SELECT id FROM thread_polling_answer WHERE thread_detail_polling_id = :thread AND user_id = :user_id";

		ThreadPollingAnswer threadPollingAns = null;
		try {
			Object result = createNativeQuery(sql).setParameter("thread", thread)
					.setParameter("user_id", user)
					.getSingleResult();
			if (result != null) {
				threadPollingAns = new ThreadPollingAnswer();
				threadPollingAns.setId(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return threadPollingAns;
	}

	public BigInteger countAllAnswer(String id) {

		StringBuilder sql = new StringBuilder().append("SELECT COUNT(tpa.id) FROM thread_polling_answer tpa ")
				.append("LEFT JOIN thread_polling_detail tpd on tpa.thread_detail_polling_id = tpd.id ")
				.append("LEFT JOIN thread_polling_header thp on tpd.thread_polling_header_id = thp.id ")
				.append("WHERE thp.id = :id");

		BigInteger count = (BigInteger) createNativeQuery(sql.toString()).setParameter("id", id).getSingleResult();

		return count;
	}

	public BigInteger countAnswer(String id) {

		String sql = "SELECT count(id) FROM thread_polling_answer WHERE thread_detail_polling_id = :id";

		BigInteger count = (BigInteger) createNativeQuery(sql).setParameter("id", id).getSingleResult();

		return count;
	}

}
