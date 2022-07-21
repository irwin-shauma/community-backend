package com.lawencon.community.service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.user.UserInsertReq;
import com.lawencon.community.model.User;

public class UserService extends BaseCoreService<User> {
	
	public InsertRes insert(UserInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			begin();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
