package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.model.Profile;
import com.lawencon.model.SearchQuery;

@Service
public class ProfileService extends BaseCoreService {
	
	@Autowired
	private ProfileDao profileDao;
	
	public Profile insert (Profile data) throws Exception {
		try {
			begin();
			profileDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return data;
	}
	
	public Profile update (Profile data) throws Exception {
		try {
			Profile profile  = profileDao.getById(data.getId());
			data.setCreatedBy(profile.getCreatedBy());
			data.setCreatedAt(profile.getCreatedAt());
			
			begin();
			profileDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return data;
	}
	
	public Profile getById(String id) throws Exception {
		return profileDao.getById(id);
	}
	
	public SearchQuery<Profile> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return profileDao.findAll(query, startPage, maxPage);
	}
	
	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = profileDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public List<Profile> findByStatus(String status) throws Exception {
		List<Profile> profile = new ArrayList<Profile>();
		try {
			profile = profileDao.findByStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}


}
