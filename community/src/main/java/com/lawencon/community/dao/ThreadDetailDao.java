package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.ThreadDetail;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.User;

@Repository
public class ThreadDetailDao extends AbstractJpaDao<ThreadDetail> {
	
	public ThreadDetail findByUserId(String userId) throws Exception {
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT td.id, td.thread_header_id, td.file_id, ")
		.append(" td.user_id, td.comment_thread WHERE td.id = :userId ");
		
		ThreadDetail threadDetail = null;
		try {
			Object result = createNativeQuery(sqlBuilder.toString())
					.setParameter("userId", userId)
					.getSingleResult();
			if (result != null) {
				Object[] objArr = (Object[]) result;
				threadDetail = new ThreadDetail();
				threadDetail.setId(objArr[0].toString());
				
				ThreadHeader threadHeader = new ThreadHeader();
				threadHeader.setId(objArr[1].toString());
				
				File file = new File();
				file.setId(objArr[2].toString());
				
				User user = new User();
				user.setId(objArr[3].toString());
				
				threadDetail.setCommentThread(objArr[4].toString());
				
				threadDetail.setThreadHeader(threadHeader);
				threadDetail.setFile(file);
				threadDetail.setUser(user);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return threadDetail;
	}

}
