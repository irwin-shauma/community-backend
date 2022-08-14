package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ThreadHeaderPolling;
import com.lawencon.community.model.ThreadPollingLike;
import com.lawencon.community.model.User;

@Repository
public class ThreadPollingLikeDao extends AbstractJpaDao<ThreadPollingLike> {

	public List<ThreadPollingLike> findByThread(String id, Integer startPage, Integer maxPage) throws Exception {
		String sql = "SELECT * FROM thread_polling_like WHERE thread_polling_header_id = :id";
		List<?> result = createNativeQuery(sql).setParameter("id", id).getResultList();

		List<ThreadPollingLike> likes = new ArrayList<>();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			ThreadPollingLike like = new ThreadPollingLike();
			like.setId(objArr[0].toString());

			User user = new User();
			user.setId(objArr[2].toString());
			like.setUserId(user);

			ThreadHeaderPolling thread = new ThreadHeaderPolling();
			thread.setId(objArr[3].toString());
			like.setThreadHeaderPolling(thread);

			likes.add(like);
		});
		return likes;
	}

	public ThreadPollingLike findByThreadAndUser(String id, String userId) throws Exception {
		String sql = "SELECT * FROM thread_polling_like WHERE thread_polling_header_id = :id AND user_id= :user";
		ThreadPollingLike like = null;
		try {
			Object result = createNativeQuery(sql).setParameter("id", id).setParameter("user", userId)
					.getSingleResult();
			if (result != null) {
				Object[] objArr = (Object[]) result;
				like = new ThreadPollingLike();
				like.setId(objArr[0].toString());

				User user = new User();
				user.setId(objArr[2].toString());
				like.setUserId(user);

				ThreadHeaderPolling thread = new ThreadHeaderPolling();
				thread.setId(objArr[3].toString());
				like.setThreadHeaderPolling(thread);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return like;
	}

	public Boolean deleteByThreadAndUser(String thread, String user) throws Exception {
		String sql = "DELETE FROM thread_polling_like WHERE thread_polling_header_id = :thread AND user_id= :user";
		int result = createNativeQuery(sql).setParameter("thread", thread).setParameter("user", user).executeUpdate();
		return result > 0;
	}

	public Long countLikes(String id) {
		String sql = "SELECT COUNT(id) FROM thread_polling_like WHERE thread_polling_header_id = :id";
		Long total = 0L;
		try {
			Object result = createNativeQuery(sql).setParameter("id", id).getSingleResult();
			if (result != null) {
				total = Long.valueOf(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;

	}

}
