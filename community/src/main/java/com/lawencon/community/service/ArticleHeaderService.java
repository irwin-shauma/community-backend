package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ArticleHeaderDao;
import com.lawencon.community.model.ArticleHeader;
import com.lawencon.model.SearchQuery;

@Service
public class ArticleHeaderService extends BaseCoreService {

	@Autowired
	private ArticleHeaderDao articleHeaderDao;
	
	public ArticleHeader insert(ArticleHeader data) throws Exception {
		try {
			begin();
			articleHeaderDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}
	
	
	public ArticleHeader update(ArticleHeader data) throws Exception {
		try {
			ArticleHeader mhsDb = articleHeaderDao.getById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());

			begin();
			articleHeaderDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	public ArticleHeader getById(String id) throws Exception {
		return articleHeaderDao.getById(id);
	}

	public SearchQuery<ArticleHeader> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return articleHeaderDao.findAll(query, startPage, maxPage);
	}

	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = articleHeaderDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
}
