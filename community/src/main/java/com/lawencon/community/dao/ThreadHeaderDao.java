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
            threadHdr.setTitle(objArr[2].toString());
            ThreadType threadType = new ThreadType();
            threadType.setId(objArr[3].toString());
            threadHdr.setThreadType(threadType);
            threadHdr.setContentThread(objArr[4].toString());

            if (objArr[11] != null) {
                File file = new File();
                file.setId(objArr[11].toString());
                threadHdr.setFile(file);
            }

            User user = new User();
            user.setId(objArr[12].toString());
            threadHdr.setUser(user);

            threadHdr.setCreatedAt(((Timestamp) objArr[5]).toLocalDateTime());
            threadHdr.setCreatedBy(objArr[6].toString());

            if (objArr[7] != null) {
                threadHdr.setUpdatedAt(((Timestamp) objArr[7]).toLocalDateTime());
            }
            if (objArr[8] != null) {
                threadHdr.setUpdatedBy(objArr[8].toString());
            }

            threadHdr.setIsActive(Boolean.valueOf(objArr[9].toString()));
            threadHdr.setVersion(Integer.valueOf(objArr[10].toString()));

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
            threadHdr.setTitle(objArr[2].toString());
            ThreadType threadType = new ThreadType();
            threadType.setId(objArr[3].toString());
            threadHdr.setThreadType(threadType);
            threadHdr.setContentThread(objArr[4].toString());

            if (objArr[11] != null) {
                File file = new File();
                file.setId(objArr[11].toString());
                threadHdr.setFile(file);
            }

            User user = new User();
            user.setId(objArr[12].toString());
            threadHdr.setUser(user);

            threadHdr.setCreatedAt(((Timestamp) objArr[5]).toLocalDateTime());
            threadHdr.setCreatedBy(objArr[6].toString());

            if (objArr[7] != null) {
                threadHdr.setUpdatedAt(((Timestamp) objArr[7]).toLocalDateTime());
            }
            if (objArr[8] != null) {
                threadHdr.setUpdatedBy(objArr[8].toString());
            }

            threadHdr.setIsActive(Boolean.valueOf(objArr[9].toString()));
            threadHdr.setVersion(Integer.valueOf(objArr[10].toString()));

            threadHdrs.add(threadHdr);
		});
		
		return threadHdrs;
	}

}
