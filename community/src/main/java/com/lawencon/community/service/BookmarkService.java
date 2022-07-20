package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.model.Bookmark;

@Service
public class BookmarkService extends BaseCoreService {
	
	@Autowired
	private BookmarkDao bookmarkDao;
	
	public Bookmark insert(Bookmark data) throws Exception {
		try {
			begin();
			bookmarkDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return data;
	}
	
	public Bookmark update(Bookmark data) throws Exception {
		try {
			Bookmark bookmark = bookmarkDao.getById(data.getId());
			data.setCreatedBy(bookmark.getCreatedBy());
			data.setCreatedAt(bookmark.getCreatedAt());
			
			begin();
			bookmarkDao.save(bookmark);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return data;
	}
	
	public Bookmark getById(String id) throws Exception {
		return bookmarkDao.getById(id);
	}

}
