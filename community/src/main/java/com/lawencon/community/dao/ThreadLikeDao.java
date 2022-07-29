package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.ThreadLike;
import com.lawencon.community.model.User;

@Repository
public class ThreadLikeDao extends AbstractJpaDao<ThreadLike>{
	
	public List<ThreadLike> findByThread(String id, Integer startPage, Integer maxPage) throws Exception {
		String sql = "SELECT *FROM thread_like WHERE thread_id = :id";
		List<?> result = createNativeQuery(sql)
				.setParameter("id", id)
				.getResultList();
		
		List<ThreadLike> likes = new ArrayList<>();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			ThreadLike like = new ThreadLike();
			like.setId(objArr[0].toString());
			
			User user = new User();
			user.setId(objArr[2].toString());
			like.setUserId(user);
			
			ThreadHeader thread = new ThreadHeader();
			thread.setId(objArr[3].toString());
			like.setThreadHeader(thread);
			
			likes.add(like);
		});
		return likes;
	}

}
