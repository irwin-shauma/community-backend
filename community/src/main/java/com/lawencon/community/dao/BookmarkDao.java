package com.lawencon.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.User;

@Repository
public class BookmarkDao extends AbstractJpaDao<Bookmark> {

	public List<Bookmark> findByUserId(String userId, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder().append("SELECT id, user_id, thread_id, is_active, version")
				.append(" FROM bookmark WHERE user_id = :user_id");
		List<?> result = createNativeQuery(sql.toString()).setParameter("user_id", userId).getResultList();
		List<Bookmark> bookmarks = new ArrayList<>();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			Bookmark bookmark = new Bookmark();
			bookmark.setId(objArr[0].toString());
			User user = new User();
			user.setId(objArr[1].toString());
			bookmark.setUser(user);

			ThreadHeader thread = new ThreadHeader();
			thread.setId(objArr[2].toString());
			bookmark.setThread(thread);

			bookmark.setIsActive(Boolean.valueOf(objArr[3].toString()));
			bookmark.setVersion(Integer.valueOf(objArr[4].toString()));

			bookmarks.add(bookmark);
		});

		return bookmarks;
	}

	public Bookmark findByThreadAndUser(String id, String userId) throws Exception {
		String sql = "SELECT id, bookmark_code FROM bookmark WHERE thread_id = :id AND user_id= :user";
		Bookmark bookmark = null;
		try {
			Object result = createNativeQuery(sql)
					.setParameter("id", id)
					.setParameter("user", userId)
					.getSingleResult();
			if (result != null) {
				Object[] objArr = (Object[]) result;
				bookmark = new Bookmark();
				bookmark.setId(objArr[0].toString());
				bookmark.setBookmarkCode(objArr[1].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookmark;
	}
	
	public Boolean deleteByThreadAndUser(String thread, String user) throws Exception {
		String sql = "DELETE FROM bookmark WHERE thread_id = :thread AND user_id= :user";
		int result = createNativeQuery(sql).setParameter("thread", thread)
				.setParameter("user",user).executeUpdate();
		return result > 0;
	}

}
