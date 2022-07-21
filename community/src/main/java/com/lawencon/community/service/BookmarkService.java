package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.dao.ThreadHeaderDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.bookmark.BookmarkInsertReq;
import com.lawencon.community.dto.bookmark.BookmarkUpdateReq;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.User;

@Service
public class BookmarkService extends BaseCoreService<Bookmark> {
	
	@Autowired
	private BookmarkDao bookmarkDao;
	
	@Autowired
	private ThreadHeaderDao threadDao;
	
	@Autowired
	private UserDao userDao;
	
	public InsertRes insert(BookmarkInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			Bookmark bookmark = new Bookmark();
			ThreadHeader thread = threadDao.getById(data.getThreadId());
			User user = userDao.getById(data.getUserId());
	
			bookmark.setThread(thread);
			bookmark.setUser(user);
			bookmark.setIsActive(true);
			
			begin();
			Bookmark inserted = save(bookmark);
			commit();
			
			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(inserted.getId());

			result.setData(insertDataRes);
			result.setMessage("Success");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	public UpdateRes update(BookmarkUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();
		try {
			Bookmark bookmark = bookmarkDao.getById(data.getId());
			ThreadHeader thread = threadDao.getById(data.getThreadId());
			User user = userDao.getById(data.getUserId());
	
			bookmark.setThread(thread);
			bookmark.setUser(user);
			bookmark.setIsActive(data.getIsActive());
			
			begin();
			Bookmark updated = save(bookmark);
			commit();
			
			UpdateDataRes dataRes = new UpdateDataRes();
			dataRes.setVersion(updated.getVersion());
			
			result.setData(dataRes);
			result.setMessage("Success");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();
		result.setMessage("Failed");
		try {
			begin();
			boolean isDeleted = bookmarkDao.deleteById(id);
			commit();
			if (isDeleted) {
				result.setMessage("Sucess");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

}
