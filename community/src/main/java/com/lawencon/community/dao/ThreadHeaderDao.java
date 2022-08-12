package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.ThreadType;
import com.lawencon.community.model.User;

@Repository
public class ThreadHeaderDao extends AbstractJpaDao<ThreadHeader> {

	public List<ThreadHeader> findAllByUserLike(String userId, String query, Integer startPage, Integer maxPage)
			throws Exception {
		StringBuilder sql = new StringBuilder().append("SELECT th.* FROM thread_headers th ")
				.append("LEFT JOIN thread_like tl ON th.id = tl.thread_id ").append("WHERE tl.user_id = :user");
		List<?> result = createNativeQuery(sql.toString()).setParameter("user", userId).getResultList();

		List<ThreadHeader> threadHdrs = new ArrayList<>();

		result.forEach(data -> {
			Object[] objArr = (Object[]) data;
            ThreadHeader threadHdr = new ThreadHeader();
            threadHdr.setId(objArr[0].toString());
            if(objArr[1] != null) {
                threadHdr.setThreadHeaderCode(objArr[1].toString());
            }
            threadHdr.setTitle(objArr[5].toString());
            ThreadType threadType = new ThreadType();
            threadType.setId(objArr[2].toString());
            threadHdr.setThreadType(threadType);
            threadHdr.setContentThread(objArr[6].toString());

            if (objArr[4] != null) {
                File file = new File();
                file.setId(objArr[4].toString());
                threadHdr.setFile(file);
            }

            User user = new User();
            user.setId(objArr[3].toString());
            threadHdr.setUser(user);

            threadHdr.setCreatedAt(((Timestamp) objArr[7]).toLocalDateTime());
            threadHdr.setCreatedBy(objArr[8].toString());

            if (objArr[9] != null) {
                threadHdr.setUpdatedAt(((Timestamp) objArr[9]).toLocalDateTime());
            }
            if (objArr[10] != null) {
                threadHdr.setUpdatedBy(objArr[10].toString());
            }

            threadHdr.setIsActive(Boolean.valueOf(objArr[11].toString()));
            threadHdr.setVersion(Integer.valueOf(objArr[12].toString()));

            threadHdrs.add(threadHdr);
		});

		return threadHdrs;
	}
	public List<ThreadHeader> findAllByUserBookmark(String userId, String query, Integer startPage, Integer maxPage)
			throws Exception {
		StringBuilder sql = new StringBuilder().append("SELECT th.* FROM thread_headers th ")
				.append("LEFT JOIN bookmark b ON th.id = b.thread_id ")
				.append("WHERE b.user_id = :user");
		List<?> result = createNativeQuery(sql.toString()).setParameter("user", userId).getResultList();
		
		List<ThreadHeader> threadHdrs = new ArrayList<>();
		
		result.forEach(data -> {
			Object[] objArr = (Object[]) data;
            ThreadHeader threadHdr = new ThreadHeader();
            threadHdr.setId(objArr[0].toString());
            if(objArr[1] != null) {
                threadHdr.setThreadHeaderCode(objArr[1].toString());
            }
            threadHdr.setTitle(objArr[5].toString());
            ThreadType threadType = new ThreadType();
            threadType.setId(objArr[2].toString());
            threadHdr.setThreadType(threadType);
            threadHdr.setContentThread(objArr[6].toString());

            if (objArr[4] != null) {
                File file = new File();
                file.setId(objArr[4].toString());
                threadHdr.setFile(file);
            }

            User user = new User();
            user.setId(objArr[3].toString());
            threadHdr.setUser(user);

            threadHdr.setCreatedAt(((Timestamp) objArr[7]).toLocalDateTime());
            threadHdr.setCreatedBy(objArr[8].toString());

            if (objArr[9] != null) {
                threadHdr.setUpdatedAt(((Timestamp) objArr[9]).toLocalDateTime());
            }
            if (objArr[10] != null) {
                threadHdr.setUpdatedBy(objArr[10].toString());
            }

            threadHdr.setIsActive(Boolean.valueOf(objArr[11].toString()));
            threadHdr.setVersion(Integer.valueOf(objArr[12].toString()));

            threadHdrs.add(threadHdr);
		});
		
		return threadHdrs;
	}
	
	public List<ThreadHeader> findAllByUserId(String userId, String query, Integer startPage, Integer maxPage)
			throws Exception {
		StringBuilder sql = new StringBuilder().append("SELECT * FROM thread_headers ")
				.append("WHERE user_id = :user");
		List<?> result = createNativeQuery(sql.toString()).setParameter("user", userId).getResultList();
		
		List<ThreadHeader> threadHdrs = new ArrayList<>();
		
		result.forEach(data -> {
			Object[] objArr = (Object[]) data;
            ThreadHeader threadHdr = new ThreadHeader();
            threadHdr.setId(objArr[0].toString());
            if(objArr[1] != null) {
                threadHdr.setThreadHeaderCode(objArr[1].toString());
            }
            threadHdr.setTitle(objArr[5].toString());
            ThreadType threadType = new ThreadType();
            threadType.setId(objArr[2].toString());
            threadHdr.setThreadType(threadType);
            threadHdr.setContentThread(objArr[6].toString());

            if (objArr[4] != null) {
                File file = new File();
                file.setId(objArr[4].toString());
                threadHdr.setFile(file);
            }

            User user = new User();
            user.setId(objArr[3].toString());
            threadHdr.setUser(user);

            threadHdr.setCreatedAt(((Timestamp) objArr[7]).toLocalDateTime());
            threadHdr.setCreatedBy(objArr[8].toString());

            if (objArr[9] != null) {
                threadHdr.setUpdatedAt(((Timestamp) objArr[9]).toLocalDateTime());
            }
            if (objArr[10] != null) {
                threadHdr.setUpdatedBy(objArr[10].toString());
            }

            threadHdr.setIsActive(Boolean.valueOf(objArr[11].toString()));
            threadHdr.setVersion(Integer.valueOf(objArr[12].toString()));

            threadHdrs.add(threadHdr);
		});
		
		return threadHdrs;
	}
	
	public Long countAllThread() throws Exception {
		String sql = "SELECT COUNT(id) FROM thread_headers ";
		Long total = 0L;
		try {
			Object result = createNativeQuery(sql).getSingleResult();
			if( result != null) {
				 total = Long.valueOf(result.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
	
	public List<ThreadHeader> findAllDesc(String query, Integer startPage, Integer maxPage)
			throws Exception {
		StringBuilder sql = new StringBuilder().append("SELECT * FROM thread_headers ")
				.append(" ORDER BY created_at DESC ");
		List<?> result = createNativeQuery(sql.toString()).getResultList();
		
		List<ThreadHeader> threadHdrs = new ArrayList<>();
		
		result.forEach(data -> {
			Object[] objArr = (Object[]) data;
            ThreadHeader threadHdr = new ThreadHeader();
            threadHdr.setId(objArr[0].toString());
            if(objArr[1] != null) {
                threadHdr.setThreadHeaderCode(objArr[1].toString());
            }
            threadHdr.setTitle(objArr[5].toString());
            ThreadType threadType = new ThreadType();
            threadType.setId(objArr[2].toString());
            threadHdr.setThreadType(threadType);
            threadHdr.setContentThread(objArr[6].toString());

            if (objArr[4] != null) {
                File file = new File();
                file.setId(objArr[4].toString());
                threadHdr.setFile(file);
            }

            User user = new User();
            user.setId(objArr[3].toString());
            threadHdr.setUser(user);

            threadHdr.setCreatedAt(((Timestamp) objArr[7]).toLocalDateTime());
            threadHdr.setCreatedBy(objArr[8].toString());

            if (objArr[9] != null) {
                threadHdr.setUpdatedAt(((Timestamp) objArr[9]).toLocalDateTime());
            }
            if (objArr[10] != null) {
                threadHdr.setUpdatedBy(objArr[10].toString());
            }

            threadHdr.setIsActive(Boolean.valueOf(objArr[11].toString()));
            threadHdr.setVersion(Integer.valueOf(objArr[12].toString()));

            threadHdrs.add(threadHdr);
		});
		
		return threadHdrs;
	}

}
