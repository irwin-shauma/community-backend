package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.role.RoleData;
import com.lawencon.community.dto.role.RoleFindByIdRes;
import com.lawencon.community.dto.role.RoleInsertReq;
import com.lawencon.community.dto.role.RoleUpdateReq;
import com.lawencon.community.model.Role;
import com.lawencon.model.SearchQuery;

@Service
public class RoleService extends BaseCoreService<Role> {
	
	@Autowired
	private RoleDao roleDao;
	
	public InsertRes insert(RoleInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			Role role = new Role();
			role.setRoleCode(data.getRoleCode());
			role.setRoleName(data.getRoleName());
			role.setCreatedBy(getAuthPrincipal());
			role.setIsActive(true);
			
			begin();
			Role inserted = roleDao.save(role);
			commit();
			
			InsertDataRes dataRes = new InsertDataRes();
			dataRes.setId(inserted.getId());
			
			result.setData(dataRes);
			result.setMessage(MessageResponse.SAVED.name());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	
	public UpdateRes update(RoleUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();
		try {
			begin();
			Role role = roleDao.getById(data.getId());
			role.setRoleCode(data.getRoleCode());
			role.setRoleName(data.getRoleName());
			role.setIsActive(data.getIsActive());
			
			Role updated = roleDao.save(role);
			commit();

			UpdateDataRes updateRes = new UpdateDataRes();
			updateRes.setVersion(updated.getVersion());
			
			result.setData(updateRes);
			result.setMessage(MessageResponse.SAVED.name());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public RoleFindByIdRes getById(String id) throws Exception {
		Role roleDb = roleDao.getById(id);
		
		RoleData data = new RoleData();
		data.setId(roleDb.getId());
		data.setRoleCode(roleDb.getRoleCode());
		data.setRoleName(roleDb.getRoleName());
		data.setIsActive(roleDb.getIsActive());
		data.setVersion(roleDb.getVersion());
		
		RoleFindByIdRes result = new RoleFindByIdRes();
		result.setData(data);
		return result;
	}

	public SearchQuery<RoleData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Role> roleDb = roleDao.findAll(query, startPage, maxPage);
		List<RoleData> roles = new ArrayList<RoleData>();
		
		roleDb.getData().forEach(role -> {
			RoleData data = new RoleData();
			data.setId(role.getId());
			data.setRoleCode(role.getRoleCode());
			data.setRoleName(role.getRoleName());
			data.setIsActive(role.getIsActive());
			data.setVersion(role.getVersion());
			
			roles.add(data);
		});
		SearchQuery<RoleData> result = new SearchQuery<>();
		result.setCount(roleDb.getCount());
		result.setData(roles);
		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = roleDao.deleteById(id);
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
