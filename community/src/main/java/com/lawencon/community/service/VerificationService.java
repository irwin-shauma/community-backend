package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.VerificationDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.verificaton.VerficationInsertReq;
import com.lawencon.community.dto.verificaton.VerificationData;
import com.lawencon.community.dto.verificaton.VerificationFindByIdRes;
import com.lawencon.community.dto.verificaton.VerificationUpdateReq;
import com.lawencon.community.model.Verification;
import com.lawencon.model.SearchQuery;

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
			Verification inserted = save(verif);
			commit();

			InsertDataRes insertRes = new InsertDataRes();
			insertRes.setId(inserted.getId());

			result.setData(insertRes);
			result.setMessage(MessageResponse.SAVED.name());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;

	}

	public UpdateRes update(VerificationUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();
		try {
			Verification verif = verifDao.getById(data.getId());
			verif.setVerificationCode(data.getVerification());
			verif.setExpired(data.getExpired());
			verif.setIsActive(data.getIsActive());

			begin();
			Verification updated = save(verif);
			commit();

			UpdateDataRes dataRes = new UpdateDataRes();
			dataRes.setVersion(updated.getVersion());

			result.setData(dataRes);
			result.setMessage(MessageResponse.SAVED.name());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	public VerificationFindByIdRes getById(String id) throws Exception {
		Verification verif = verifDao.getById(id);

		VerificationData data = new VerificationData();
		data.setId(verif.getId());
		data.setVerification(verif.getVerificationCode());
		data.setExpired(verif.getExpired());
		data.setIsActive(verif.getIsActive());
		data.setVersion(verif.getVersion());

		VerificationFindByIdRes result = new VerificationFindByIdRes();
		result.setData(data);

		return result;
	}

	public SearchQuery<VerificationData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Verification> dataDb = verifDao.findAll(query, startPage, maxPage);

		List<VerificationData> data = new ArrayList<>();
		dataDb.getData().forEach(verif -> {
			VerificationData verification = new VerificationData();
			verification.setId(verif.getId());
			verification.setVerification(verif.getVerificationCode());
			verification.setExpired(verif.getExpired());
			verification.setIsActive(verif.getIsActive());
			verification.setVersion(verif.getVersion());

			data.add(verification);
		});

		SearchQuery<VerificationData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(data);
		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();
		result.setMessage(MessageResponse.FAILED.name());
		try {
			begin();
			boolean isDeleted = verifDao.deleteById(id);
			commit();
			if (isDeleted) {
				result.setMessage(MessageResponse.DELETED.name());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

}
