package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.ThreadHeaderDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.threadheader.ThreadHeaderData;
import com.lawencon.community.dto.threadheader.ThreadHeaderFindByIdRes;
import com.lawencon.community.dto.threadheader.ThreadHeaderInsertReq;
import com.lawencon.community.dto.threadheader.ThreadHeaderUpdateReq;
import com.lawencon.community.model.File;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.ThreadType;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadHeaderService extends BaseCoreService<ThreadHeader> {

	@Autowired
	private ThreadHeaderDao threadHdrDao;

	@Autowired
	private FileDao fileDao;

	public InsertRes insert(ThreadHeaderInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			ThreadHeader threadHdr = new ThreadHeader();
			threadHdr.setTitle(data.getTitle());
			threadHdr.setContentThread(data.getContentThread());

			ThreadType threadType = new ThreadType();
			threadType.setId(data.getThreadTypeId());

			threadHdr.setThreadType(threadType);
			threadHdr.setIsActive(true);

			begin();
			if (data.getFileName() != null) {
				File file = new File();
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				File insertedFile = fileDao.save(file);
				threadHdr.setFile(insertedFile);
			} else {
				threadHdr.setFile(null);
			}

			ThreadHeader inserted = save(threadHdr);
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

	public UpdateRes update(ThreadHeaderUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();
		try {
			ThreadHeader threadHdr = threadHdrDao.getById(data.getId());
			threadHdr.setTitle(data.getTitle());
			threadHdr.setContentThread(data.getContentThread());
			threadHdr.setIsActive(data.getIsActive());

			ThreadType threadType = new ThreadType();
			threadType.setId(data.getThreadTypeId());

			threadHdr.setThreadType(threadType);
			threadHdr.setIsActive(true);

			File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());

			begin();
			File insertedFile = fileDao.save(file);
			threadHdr.setFile(insertedFile);
			ThreadHeader updated = save(threadHdr);
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

	public ThreadHeaderFindByIdRes getById(String id) throws Exception {
		ThreadHeader threadHdr = threadHdrDao.getById(id);

		ThreadHeaderData thread = new ThreadHeaderData();
		thread.setId(threadHdr.getId());
		thread.setTitle(threadHdr.getTitle());

		thread.setThreadTypeId(threadHdr.getThreadType().getId());
		thread.setContentThread(threadHdr.getContentThread());
		thread.setCreatedAt(threadHdr.getCreatedAt());
		thread.setVersion(threadHdr.getVersion());
		thread.setIsActive(threadHdr.getIsActive());

		ThreadHeaderFindByIdRes result = new ThreadHeaderFindByIdRes();
		result.setData(thread);

		return result;
	}

	public SearchQuery<ThreadHeaderData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadHeader> dataDb = threadHdrDao.findAll(query, startPage, maxPage);

		List<ThreadHeaderData> data = new ArrayList<>();
		dataDb.getData().forEach(threadHdr -> {
			ThreadHeaderData thread = new ThreadHeaderData();
			thread.setId(threadHdr.getId());
			thread.setTitle(threadHdr.getTitle());

			thread.setThreadTypeId(threadHdr.getThreadType().getId());
			thread.setContentThread(threadHdr.getContentThread());
			thread.setCreatedAt(threadHdr.getCreatedAt());
			thread.setVersion(threadHdr.getVersion());
			thread.setIsActive(threadHdr.getIsActive());

			data.add(thread);
		});
		SearchQuery<ThreadHeaderData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(data);
		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();
		result.setMessage(MessageResponse.FAILED.getMessageResponse());
		try {
			begin();
			boolean isDeleted = threadHdrDao.deleteById(id);
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