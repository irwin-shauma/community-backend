package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.ThreadDetailDao;
import com.lawencon.community.dao.ThreadHeaderDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.threaddetail.ThreadDetailData;
import com.lawencon.community.dto.threaddetail.ThreadDetailFindByIdRes;
import com.lawencon.community.dto.threaddetail.ThreadDetailInsertReq;
import com.lawencon.community.dto.threaddetail.ThreadDetailUpdateReq;
import com.lawencon.community.model.ThreadDetail;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadDetailService extends BaseCoreService<ThreadDetail> {

	@Autowired
	private ThreadDetailDao threadDetailDao;

	@Autowired
	private ThreadHeaderDao threadHeaderDao;


	@Autowired
	private UserDao userDao;

	public InsertRes insert(ThreadDetailInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {
			ThreadDetail threadDetail = new ThreadDetail();
			threadDetail.setThreadDetailCode(code);
			ThreadHeader threadHeader = new ThreadHeader();
			threadHeader.setId(data.getThreadHeaderId());
			threadDetail.setThreadHeader(threadHeader);

			User user = new User();
			user.setId(getAuthPrincipal());
			threadDetail.setUser(user);
			threadDetail.setCommentThread(data.getCommentThread());

			threadDetail.setIsActive(true);

			begin();
			ThreadDetail threadDetailInsert = save(threadDetail);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(threadDetailInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public UpdateRes update(ThreadDetailUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			ThreadDetail threadDetailInsert = threadDetailDao.getById(data.getId());

			ThreadHeader threadHeader = threadHeaderDao.getById(data.getThreadHeaderId());
			threadDetailInsert.setThreadHeader(threadHeader);

			User user = userDao.getById(data.getUserId());
			threadDetailInsert.setUser(user);

			threadDetailInsert.setCommentThread(data.getCommentThread());
			threadDetailInsert.setIsActive(true);
			threadDetailInsert.setVersion(data.getVersion());

			begin();
			ThreadDetail threadDetailUpdate = threadDetailDao.save(threadDetailInsert);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(threadDetailUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	public ThreadDetailFindByIdRes getById(String id) throws Exception {
		ThreadDetail threadDetailDb = threadDetailDao.getById(id);

		ThreadDetailData data = new ThreadDetailData();
		data.setId(threadDetailDb.getId());
		data.setThreadHeaderId(threadDetailDb.getThreadHeader().getId());
		data.setUserId(threadDetailDb.getUser().getId());
		data.setCommentThread(threadDetailDb.getCommentThread());
		data.setIsActive(threadDetailDb.getIsActive());
		data.setVersion(threadDetailDb.getVersion());

		ThreadDetailFindByIdRes result = new ThreadDetailFindByIdRes();
		result.setData(data);

		return result;
	}

	public SearchQuery<ThreadDetailData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadDetail> dataDb = threadDetailDao.findAll(query, startPage, maxPage);

		List<ThreadDetailData> threadDetailDataList = new ArrayList<ThreadDetailData>();

		dataDb.getData().forEach(threadDetail -> {
			ThreadDetailData data = new ThreadDetailData();
			data.setId(threadDetail.getId());
			data.setThreadHeaderId(threadDetail.getThreadHeader().getId());
			data.setUserId(threadDetail.getUser().getId());
			data.setVersion(threadDetail.getVersion());

			threadDetailDataList.add(data);
		});

		SearchQuery<ThreadDetailData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(threadDetailDataList);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = threadDetailDao.deleteById(id);
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
