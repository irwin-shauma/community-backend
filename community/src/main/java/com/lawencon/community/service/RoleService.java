package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.model.Role;

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
}
