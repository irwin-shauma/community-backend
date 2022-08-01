package com.lawencon.community.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<ThreadDetail> findAllByHeader(String id) throws Exception {
		String sql = "SELECT * FROM thread_details WHERE thread_header_id = :id";
		List<?> result = createNativeQuery(sql).setParameter("id", id)
				.getResultList();
		List<ThreadDetail> threadDetails = new ArrayList<>();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			ThreadDetail threadDetail = new ThreadDetail();
			threadDetail.setId(objArr[0].toString());
			
			ThreadHeader threadHdr = new ThreadHeader();
			threadHdr.setId(objArr[2].toString());
			threadDetail.setThreadHeader(threadHdr);
			User user = new User();
			user.setId(objArr[3].toString());
			threadDetail.setUser(user);
			threadDetail.setCommentThread(objArr[4].toString());
			threadDetail.setCreatedAt(((Timestamp) objArr[5]).toLocalDateTime());
			
			threadDetails.add(threadDetail);
		});
		return threadDetails;
	}
	
	public BigInteger countComment(String id) {
		BigInteger count = (BigInteger) createNativeQuery("SELECT COUNT(id) FROM thread_details WHERE thread_header_id = :id")
				.setParameter("id", id).getSingleResult();
		return count;
	}

}
