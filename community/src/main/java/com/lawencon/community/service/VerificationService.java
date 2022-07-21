package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.VerificationDao;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.verificaton.VerficationInsertReq;
import com.lawencon.community.model.Verification;

public class VerificationService extends BaseCoreService<Verification> {

	@Autowired
	private VerificationDao verifDao;

	public InsertRes insert(VerficationInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			Verification verif = new Verification();
			verif.setVerificationCode(data.getVerification());
			verif.setExpired(data.getExpired());
			verif.setIsActive(true);
			
			begin();
			save(verif);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;

	}

}
