package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadHeaderPolling;
import com.lawencon.community.model.ThreadPollingDetail;

@Repository
public class ThreadPollingDetailDao extends AbstractJpaDao<ThreadPollingDetail>{
	
	public List<ThreadPollingDetail> findByHeader(String id) throws Exception {
		String sql = "SELECT * FROM thread_polling_detail WHERE thread_header_polling_id = :id";
		
		List<?> result = createNativeQuery(sql)
						.setParameter("id", id)
						.getResultList();
		List<ThreadPollingDetail> threadDtls = new ArrayList<ThreadPollingDetail>();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			ThreadPollingDetail threadDtl = new ThreadPollingDetail();
			threadDtl.setId(objArr[0].toString());
			
			ThreadHeaderPolling threadHdr = new ThreadHeaderPolling();
			threadHdr.setId(objArr[2].toString());
			threadDtl.setThreadHeaderPolling(threadHdr);
			
			threadDtl.setQuestion(objArr[3].toString());		
			threadDtl.setIsActive(Boolean.valueOf(objArr[8].toString()));
			threadDtl.setVersion(Integer.valueOf(objArr[9].toString()));
			
			threadDtls.add(threadDtl);
		});
		
		return threadDtls;
	}

}
