package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.User;

@Repository
public class BookmarkDao extends AbstractJpaDao<Bookmark> {
	
	public Bookmark findByUserId(String userId) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append("SELECT id, user_id, thread_id, is_active, version")
				.append(" FROM bookmark WHERE user_id = :user_id");
		Bookmark bookmark = null;
		
		try {
			Object result = createNativeQuery(sql.toString())
					.setParameter("user_id", userId)
					.getResultList();
			if (result != null) {
				Object[] objArr = (Object[]) result;
				bookmark = new Bookmark();
				bookmark.setId(objArr[0].toString());
				User user = new User();
				user.setId(objArr[1].toString());
				bookmark.setUser(user);
				
				ThreadHeader thread = new ThreadHeader();
				thread.setId(objArr[2].toString());
				bookmark.setThread(thread);
				
				bookmark.setIsActive(Boolean.valueOf(objArr[3].toString()));
				bookmark.setVersion(Integer.valueOf(objArr[4].toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bookmark;
	}

}
