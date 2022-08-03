package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.ThreadDetailDao;
import com.lawencon.community.dao.ThreadHeaderDao;
import com.lawencon.community.dao.ThreadLikeDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.threadheader.ThreadDetailData;
import com.lawencon.community.dto.threadheader.ThreadHeaderData;
import com.lawencon.community.dto.threadheader.ThreadHeaderFindByIdRes;
import com.lawencon.community.dto.threadheader.ThreadHeaderInsertReq;
import com.lawencon.community.dto.threadheader.ThreadHeaderUpdateReq;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.ThreadDetail;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.ThreadType;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadHeaderService extends BaseService<ThreadHeader> {

	@Autowired
	private ThreadHeaderDao threadHdrDao;
	
	@Autowired
	private ThreadLikeDao threadLikeDao;
	
	@Autowired
	private ThreadDetailDao threadDetailDao;

	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private UserDao userDao;

	public InsertRes insert(ThreadHeaderInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {
			ThreadHeader threadHdr = new ThreadHeader();
			threadHdr.setThreadHeaderCode(code);
			threadHdr.setTitle(data.getTitle());
			threadHdr.setContentThread(data.getContentThread());
			ThreadType threadType = new ThreadType();
			threadType.setId(data.getThreadTypeId());
			
			User user = userDao.getById(getUserId());
			
			threadHdr.setUser(user);
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
		if(threadHdr.getFile() != null) {
			thread.setFileId(threadHdr.getFile().getId());			
		}
		thread.setUserId(threadHdr.getUser().getId());
		thread.setCreatedBy(threadHdr.getCreatedBy());
		User user = userDao.getById(threadHdr.getUser().getId());
		
		Profile profile = profileDao.getById(user.getProfile().getId());
		thread.setFullName(profile.getFullName());
		if(profile.getFile() != null) {
			thread.setUserPhoto(profile.getFile().getId());
		}
	
		
		thread.setCreatedAt(threadHdr.getCreatedAt());
		thread.setVersion(threadHdr.getVersion());
		thread.setIsActive(threadHdr.getIsActive());
		
		int countLike = threadLikeDao.countLikes(threadHdr.getId()).intValue();
		thread.setCountLike(countLike);
		
		int countComment = threadDetailDao.countComment(threadHdr.getId()).intValue();
		thread.setCountComment(countComment);
		
		List<ThreadDetailData> listDetail = new ArrayList<>();
		try {
			List<ThreadDetail> threadDtls = threadDetailDao.findAllByHeader(threadHdr.getId());
			for (int i = 0; i < threadDtls.size(); i++) {
				ThreadDetailData threadDtl = new ThreadDetailData();
				threadDtl.setId(threadDtls.get(i).getId());
				threadDtl.setThreadHeaderId(threadDtls.get(i).getThreadHeader().getId());
				threadDtl.setUserId(threadDtls.get(i).getUser().getId());
				
				User users = userDao.getById(threadDtls.get(i).getUser().getId());
				Profile profiles = profileDao.getById(users.getProfile().getId());
				
				threadDtl.setFullName(profiles.getFullName());
				if(profiles.getFile() != null) {
					threadDtl.setUserPhoto(profiles.getFile().getId());;
				}
				
				
				threadDtl.setCommentThread(threadDtls.get(i).getCommentThread());
				threadDtl.setCreatedAt(threadDtls.get(i).getCreatedAt());
				
				listDetail.add(threadDtl);
			}
			thread.setThreadDetail(listDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			if(threadHdr.getFile() != null) {
				thread.setFileId(threadHdr.getFile().getId());				
			}else {
				thread.setFileId(null);
			}
			thread.setUserId(threadHdr.getUser().getId());
			thread.setCreatedBy(threadHdr.getCreatedBy());
			User user = userDao.getById(threadHdr.getUser().getId());
			
			Profile profile = profileDao.getById(user.getProfile().getId());
			thread.setFullName(profile.getFullName());
			if(profile.getFile() != null) {
				thread.setUserPhoto(profile.getFile().getId());
			}
			
			
			thread.setCreatedAt(threadHdr.getCreatedAt());
			thread.setVersion(threadHdr.getVersion());
			thread.setIsActive(threadHdr.getIsActive());
			
			int countLike = threadLikeDao.countLikes(threadHdr.getId()).intValue();
			thread.setCountLike(countLike);
			
			int countComment = threadDetailDao.countComment(threadHdr.getId()).intValue();
			thread.setCountComment(countComment);
			
			List<ThreadDetailData> listDetail = new ArrayList<>();
			try {
				List<ThreadDetail> threadDtls = threadDetailDao.findAllByHeader(threadHdr.getId());
				for (int i = 0; i < threadDtls.size(); i++) {
					ThreadDetailData threadDtl = new ThreadDetailData();
					threadDtl.setId(threadDtls.get(i).getId());
					threadDtl.setThreadHeaderId(threadDtls.get(i).getThreadHeader().getId());
					threadDtl.setUserId(threadDtls.get(i).getUser().getId());
					
					User users = userDao.getById(threadDtls.get(i).getUser().getId());
					Profile profiles = profileDao.getById(users.getProfile().getId());
					threadDtl.setFullName(profiles.getFullName());
					threadDtl.setUserPhoto(profile.getFile().getId());
					
					threadDtl.setCommentThread(threadDtls.get(i).getCommentThread());
					threadDtl.setCreatedAt(threadDtls.get(i).getCreatedAt());
					
					listDetail.add(threadDtl);
				}
				thread.setThreadDetail(listDetail);
			} catch (Exception e) {
				e.printStackTrace();
			}

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