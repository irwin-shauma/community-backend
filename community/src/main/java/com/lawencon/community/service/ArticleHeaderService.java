package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.ArticleHeaderDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.articleheader.ArticleHeaderData;
import com.lawencon.community.dto.articleheader.ArticleHeaderFindByIdRes;
import com.lawencon.community.dto.articleheader.ArticleHeaderInsertReq;
import com.lawencon.community.dto.articleheader.ArticleHeaderUpdateReq;
import com.lawencon.community.model.ArticleHeader;
import com.lawencon.community.model.File;
import com.lawencon.model.SearchQuery;

@Service
public class ArticleHeaderService extends BaseCoreService<ArticleHeader> {

	@Autowired
	private ArticleHeaderDao articleHeaderDao;

	@Autowired
	private FileDao fileDao;

	public InsertRes insert(ArticleHeaderInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {
			begin();

			ArticleHeader articleHdr = new ArticleHeader();
			articleHdr.setArticleHeaderCode(code);
			articleHdr.setTitle(data.getTitle());
			articleHdr.setContents(data.getContents());

			if (data.getFileName() != null) {
				File file = new File();
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				File fileRes = fileDao.save(file);
				articleHdr.setFile(fileRes);
			}
			articleHdr.setIsActive(true);

			ArticleHeader inserted = articleHeaderDao.save(articleHdr);
			commit();

			InsertDataRes insertRes = new InsertDataRes();
			insertRes.setId(inserted.getId());

			result.setData(insertRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public UpdateRes update(ArticleHeaderUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();
		try {
			begin();
			ArticleHeader dataDb = articleHeaderDao.getById(data.getId());
			dataDb.setTitle(data.getTitle());
			dataDb.setContents(data.getContents());
			dataDb.setIsActive(data.getIsActive());

			ArticleHeader updated = null;
			if (data.getFileId() != null && data.getFileName() != null) {
				File file = fileDao.getById(data.getFileId());
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				fileDao.save(file);
				updated = save(dataDb);
			} else if (data.getFileId() == null && data.getFileName() != null) {
				File file = new File();
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				File fileRes = fileDao.save(file);
				dataDb.setFile(fileRes);
			} else if (data.getFileId() == null) {
				File formerFile = fileDao.getByIdWithoutDetach(dataDb.getFile().getId());
				dataDb.setFile(null);
				fileDao.delete(formerFile);
			}
			updated = save(dataDb);
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

	public ArticleHeaderFindByIdRes getById(String id) throws Exception {
		ArticleHeader articleHdr = articleHeaderDao.getById(id);

		ArticleHeaderData data = new ArticleHeaderData();
		data.setId(articleHdr.getId());
		if (articleHdr.getFile() != null) {
			data.setFileId(articleHdr.getFile().getId());
		}
		data.setTitle(articleHdr.getTitle());
		data.setContents(articleHdr.getContents());
		data.setIsActive(articleHdr.getIsActive());
		data.setVersion(articleHdr.getVersion());

		ArticleHeaderFindByIdRes result = new ArticleHeaderFindByIdRes();
		result.setData(data);

		return result;
	}

	public SearchQuery<ArticleHeaderData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ArticleHeader> dataDb = articleHeaderDao.findAll(query, startPage, maxPage);

		List<ArticleHeaderData> dataList = new ArrayList<>();
		dataDb.getData().forEach(articleHdr -> {
			ArticleHeaderData data = new ArticleHeaderData();
			data.setId(articleHdr.getId());
			if (articleHdr.getFile() != null) {
				data.setFileId(articleHdr.getFile().getId());
			}
			data.setTitle(articleHdr.getTitle());
			data.setContents(articleHdr.getContents());
			data.setIsActive(articleHdr.getIsActive());
			data.setVersion(articleHdr.getVersion());

			dataList.add(data);
		});

		SearchQuery<ArticleHeaderData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(dataList);
		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();
		result.setMessage(MessageResponse.FAILED.getMessageResponse());
		try {
			begin();

			ArticleHeader articleHeader = articleHeaderDao.getById(id);
			if (articleHeader.getFile() != null) {
				fileDao.deleteById(articleHeader.getFile().getId());
			}

			boolean isDeleted = articleHeaderDao.deleteById(id);
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
