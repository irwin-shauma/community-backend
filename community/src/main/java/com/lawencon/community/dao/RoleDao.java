package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.Role;
import com.lawencon.model.SearchQuery;

public class RoleDao extends AbstractJpaDao<Role>{
	
	public Role save(Role data) throws Exception {
		return super.save(data);
	}
	
	public Role findById(String id) throws Exception {
		return getById(id);
	}
	
	public SearchQuery<Role> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Role> sq = new SearchQuery<>();
		List<Role> data = null;
		
		if( startPage == null || maxPage == null) {
			data = getAll();
			sq.setData(data);
		} else {
			if(query == null) {
				data = getAll(startPage, maxPage);
				int count = countAll().intValue();
				
				sq.setData(data);
				sq.setCount(count);
			} else {
				return super.getAll(query, startPage, maxPage);
			}
		}
		
		return sq;
	}
	
	
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}
