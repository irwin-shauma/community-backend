package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadPollingAnswer;

@Repository
public class ThreadPollingAnswerDao extends AbstractJpaDao<ThreadPollingAnswer> {

	public ThreadPollingAnswer findByThreadAndUser(String thread, String user) throws Exception {
		String sql = "SELECT id FROM thread_polling_answer WHERE thread_detail_polling_id = :thread AND user_id = :user_id";

		ThreadPollingAnswer threadPollingAns = new ThreadPollingAnswer();
		try {
			Object result = createNativeQuery(sql).setParameter("thread", thread)
					.setParameter("user_id", user)
					.getSingleResult();
			if (result != null) {
				threadPollingAns = new ThreadPollingAnswer();
				Object[] objArr = (Object[]) result;
				threadPollingAns.setId(objArr[0].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return threadPollingAns;
	}

	public Long countAllAnswer(String id) {

		StringBuilder sql = new StringBuilder().append("SELECT COUNT(tpa.id) FROM thread_polling_answer tpa ")
				.append("LEFT JOIN thread_polling_detail tpd on tpa.thread_detail_polling_id = tpd.id ")
				.append("LEFT JOIN thread_header_polling thp on tpd.thread_header_polling_id = thp.id ")
				.append("WHERE thp.id = :id");

		Long count = 0L;
		try {
			Object result = createNativeQuery(sql.toString())
					.setParameter("id", id)
					.getSingleResult();
			if(result != null) {
				count = Long.valueOf(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public Long countAnswer(String id) {

		String sql = "SELECT count(id) FROM thread_polling_answer WHERE thread_detail_polling_id = :id";

		Long count = 0L;
		
		try {
			Object result = createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			
			if(result != null) {
				count = Long.valueOf(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

}
