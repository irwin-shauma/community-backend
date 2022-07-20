package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.model.Role;
import com.lawencon.model.SearchQuery;

@Service
public class RoleService extends BaseCoreService {
	
	@Autowired
	private RoleDao roleDao;
	
	public Role insert(Role data) throws Exception {
		try {
			begin();
			roleDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}
	
	
	public Role update(Role data) throws Exception {
		try {
			Role mhsDb = roleDao.getById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());

			begin();
			roleDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	public Role getById(String id) throws Exception {
		return roleDao.getById(id);
	}

	public SearchQuery<Role> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return roleDao.findAll(query, startPage, maxPage);
	}

	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = roleDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

}
