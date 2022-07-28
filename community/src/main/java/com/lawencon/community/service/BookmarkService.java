package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.dao.ThreadHeaderDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.bookmark.BookmarkData;
import com.lawencon.community.dto.bookmark.BookmarkFindByIdRes;
import com.lawencon.community.dto.bookmark.BookmarkInsertReq;
import com.lawencon.community.dto.bookmark.BookmarkUpdateReq;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

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
			User user = userDao.getById(getAuthPrincipal());

			bookmark.setThread(thread);
			bookmark.setUser(user);
			bookmark.setIsActive(true);

			begin();
			Bookmark inserted = save(bookmark);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(inserted.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
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
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	public SearchQuery<BookmarkData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Bookmark> dataDb = bookmarkDao.findAll(query, startPage, maxPage);
		List<BookmarkData> bookmarks = new ArrayList<>();

		dataDb.getData().forEach(bookmark -> {
			BookmarkData data = new BookmarkData();
			data.setId(bookmark.getId());
			data.setUserId(bookmark.getUser().getId());
			data.setThreadId(bookmark.getThread().getId());

			bookmarks.add(data);
		});
		SearchQuery<BookmarkData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(bookmarks);
		return result;
	}

	public SearchQuery<BookmarkData> findAllByUser(Integer startPage, Integer maxPage) throws Exception {
		List<Bookmark> bookmarks = bookmarkDao.findByUserId(getAuthPrincipal(), startPage, maxPage);
		int count = bookmarkDao.countAll().intValue();

		List<BookmarkData> resultList = new ArrayList<BookmarkData>();

		bookmarks.forEach(bookmark -> {
			try {
				BookmarkData data = new BookmarkData();
				data.setId(bookmark.getId());
				data.setUserId(bookmark.getUser().getId());
				data.setThreadId(bookmark.getThread().getId());

				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		SearchQuery<BookmarkData> result = new SearchQuery<>();
		result.setCount(count);
		result.setData(resultList);
		return result;
	}

	public BookmarkFindByIdRes getById(String id) throws Exception {
		Bookmark bookmark = bookmarkDao.getById(id);

		BookmarkData data = new BookmarkData();
		data.setId(bookmark.getId());
		data.setUserId(bookmark.getUser().getId());
		data.setThreadId(bookmark.getThread().getId());

		BookmarkFindByIdRes result = new BookmarkFindByIdRes();
		result.setData(data);
		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();
		result.setMessage(MessageResponse.FAILED.getMessageResponse());
		try {
			begin();
			boolean isDeleted = bookmarkDao.deleteById(id);
			commit();
			if (isDeleted) {
				result.setMessage(MessageResponse.DELETED.getMessageResponse());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

}
