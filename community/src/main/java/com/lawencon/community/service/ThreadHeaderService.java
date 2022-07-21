package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ThreadHeaderDao;
import com.lawencon.community.dao.ThreadTypeDao;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.threadheader.ThreadHeaderInsertReq;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.ThreadType;

public class ThreadHeaderService extends BaseCoreService<ThreadHeader> {
	
	@Autowired
	private ThreadHeaderDao threadHdrDao;
	private ThreadTypeDao threadTypeDao;
	
	public InsertRes insert(ThreadHeaderInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			ThreadHeader threadHdr = new ThreadHeader();
			threadHdr.setTitle(data.getTitle());
			threadHdr.setContentThread(data.getContent());
			
			ThreadType threadType = threadTypeDao.getById(data.getThreadTypeId());
			
			threadHdr.setThreadType(threadType);
			threadHdr.setIsActive(true);
			
			begin();
			ThreadHeader inserted = new ThreadHeader();
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

}