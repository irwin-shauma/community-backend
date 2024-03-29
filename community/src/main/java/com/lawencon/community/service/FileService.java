package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.file.FileData;
import com.lawencon.community.dto.file.FileInsertReq;
import com.lawencon.community.model.File;
import com.lawencon.model.SearchQuery;

@Service
public class FileService extends BaseCoreService<File>{
	
	@Autowired
	private FileDao fileDao;
	
	public InsertRes insert(FileInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {
			File file = new File();
			file.setFileCode(code);
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExt());
			file.setIsActive(true);

			begin();
			File fileInsert = save(file);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(fileInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());	
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
	
	
	public File getById(String id) {
		File file = fileDao.getById(id);
		return file;
	}
	
	public SearchQuery<FileData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<File> dataDb = fileDao.findAll(query, startPage, maxPage);

		List<FileData> fileDataList = new ArrayList<FileData>();

		dataDb.getData().forEach(file -> {
			FileData data = new FileData();
			data.setId(file.getId());
			data.setFileName(file.getFileName());
			data.setFileExt(file.getFileExtension());
			data.setIsActive(file.getIsActive());
			data.setVersion(file.getVersion());
			fileDataList.add(data);
		});

		SearchQuery<FileData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(fileDataList);

		return result;
	}
	
	
	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = fileDao.deleteById(id);
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
