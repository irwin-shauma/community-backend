package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.User;

@Repository
public class ProfileDao extends AbstractJpaDao<Profile> {

	public List<Profile> findByStatus(String status) throws Exception {
		String sql = "SELECT * FROM profile WHERE status = :status";

		List<?> result = createNativeQuery(sql)
				.setParameter("status", status)
				.getResultList();

		List<Profile> profiles = new ArrayList<>();

		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			Profile profile = new Profile();
			profile.setId(objArr[0].toString());
			profile.setProfileCode(objArr[1].toString());
			profile.setFullName(objArr[2].toString());
			profile.setCompany(objArr[3].toString());
			profile.setIndustry(objArr[4].toString());
//			profile.setStatus(objArr[5].toString());
//			profile.setStatusDuration(((Timestamp) objArr[6]).toLocalDateTime());
			User user = new User();
			user.setId(objArr[7].toString());
			profile.setUser(user);
			File file = new File();
			if (objArr[8] != null) {
				file.setId(objArr[8].toString());
				profile.setFile(file);
			}
			profile.setCreatedBy(objArr[9].toString());
			if (objArr[10] != null) {
				profile.setCreatedAt(((Timestamp) objArr[10]).toLocalDateTime());
			}
			profile.setUpdatedBy(objArr[11].toString());
			if (objArr[12] != null) {
				profile.setUpdatedAt(((Timestamp) objArr[12]).toLocalDateTime());
			}
			profile.setIsActive(Boolean.valueOf(objArr[13].toString()));
			profile.setVersion(Integer.valueOf(objArr[14].toString()));

			profiles.add(profile);

		});
		return profiles;
	}
	
	public Profile findByUserId(String userId) throws Exception {
		String sql = " SELECT * FROM profile WHERE user_id = :user_id ";
		Profile profile = null;
		try {
			Object result = createNativeQuery(sql)
							.setParameter("user_id", userId)
							.getSingleResult();
			if (result != null) {
				Object[] objArr = (Object[]) result;
				profile = new Profile();
				profile.setId(objArr[0].toString());
				if(objArr[1] != null) {
					profile.setProfileCode(objArr[1].toString());
				}
				
				profile.setFullName(objArr[2].toString());
				profile.setCompany(objArr[3].toString());
				profile.setIndustry(objArr[4].toString());
				profile.setPosition(objArr[5].toString());
//				profile.setStatus(objArr[6].toString());
//				profile.setStatusDuration(((Timestamp) objArr[7]).toLocalDateTime());
				User user = new User();
				user.setId(objArr[8].toString());
				profile.setUser(user);
				File file = new File();
				if (objArr[9] != null) {
					file.setId(objArr[9].toString());
					profile.setFile(file);
				}
				if (objArr[10] != null) {
					profile.setCreatedBy(objArr[10].toString());
				}
				if (objArr[11] != null) {
					profile.setCreatedAt(((Timestamp) objArr[11]).toLocalDateTime());
				}
				if (objArr[12] != null) {
					profile.setUpdatedBy(objArr[12].toString());
				}
				if (objArr[13] != null) {
					profile.setUpdatedAt(((Timestamp) objArr[13]).toLocalDateTime());
				}
				profile.setIsActive(Boolean.valueOf(objArr[14].toString()));
				profile.setVersion(Integer.valueOf(objArr[15].toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
