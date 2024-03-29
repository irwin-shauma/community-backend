package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadType;

@Repository
public class ThreadTypeDao extends AbstractJpaDao<ThreadType> {
	
	public ThreadType getRegularType() throws Exception {
		String sql = "SELECT id, thread_type , thread_types_code FROM thread_types WHERE thread_type = 'Regular'";
		ThreadType threadType = null;
		try {
			Object result = createNativeQuery(sql).getSingleResult();
			
			if(result != null) {
				Object[] objArr = (Object[]) result;
				threadType = new ThreadType();
				threadType.setId(objArr[0].toString());
				threadType.setThreadType(objArr[1].toString());
				threadType.setThreadTypeCode(objArr[2].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return threadType;
	}

}
