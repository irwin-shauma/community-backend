package com.lawencon.community.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadHeaderPolling;

@Repository
public class ThreadHeaderPollingDao extends AbstractJpaDao<ThreadHeaderPolling> {

	public List<ThreadHeaderPolling> findByUserId(String userId, String query, Integer startPage, Integer maxPage) throws Exception {
		String sql = "SELECT * FROM thread_header_polling WHERE user_id = :user";

		List<?> result = createNativeQuery(sql).setParameter("user", userId).getResultList();

		List<ThreadHeaderPolling> listThread = new ArrayList<>();

		result.forEach(threadHdr -> {
			Object[] objArr = (Object[]) threadHdr;
			ThreadHeaderPolling data = new ThreadHeaderPolling();
			data.setId(objArr[0].toString());
			data.setThreadHeaderPollingCode(objArr[1].toString());
			data.setTitlePolling(objArr[2].toString());
			data.setContentPolling(objArr[3].toString());
			data.setPollingQuestion(objArr[11].toString());
			data.setDuration(((Date) objArr[10]).toLocalDate());
			if (objArr[4] != null) {
				data.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
			}
			
			data.setCreatedBy(objArr[5].toString());
			
			if (objArr[6] != null) {
				data.setUpdatedAt(((Timestamp) objArr[6]).toLocalDateTime());
			}
			
			if (objArr[7] != null) {
				data.setUpdatedBy(objArr[7].toString());
			}
			data.setIsActive(Boolean.valueOf(objArr[8].toString()));
			data.setVersion(Integer.valueOf(objArr[9].toString()));
			
			listThread.add(data);
		});
		return listThread;
	}

}
