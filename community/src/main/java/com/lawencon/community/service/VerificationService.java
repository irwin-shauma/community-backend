package com.lawencon.community.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.lawencon.community.util.EmailComponent;
import com.lawencon.model.SearchQuery;

@Service
public class VerificationService extends BaseCoreService<Verification> {

	@Autowired
	private VerificationDao verifDao;

	@Autowired
	private EmailComponent emailComponent;

	public InsertRes insert(VerficationInsertReq email) throws Exception {
		InsertRes result = new InsertRes();
		try {
			Verification verif = new Verification();
			String verification = RandomStringUtils.randomAlphanumeric(5);
			verif.setVerificationCode(verification);
			verif.setExpired(LocalDateTime.now().plusHours(2));
			verif.setIsActive(true);

			Map<String, Object> template = new HashMap<String, Object>();
			template.put("code", verification);

			begin();
			Verification inserted = save(verif);
			commit();

			new Thread(() -> {
				try {
					emailComponent.sendMessageUsingFreemarkerTemplate(email.getEmail(), "Verification Code", template);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();

			InsertDataRes insertRes = new InsertDataRes();
			insertRes.setId(inserted.getId());

			result.setData(insertRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
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
			verif.setVersion(data.getVersion());

			begin();
			Verification updated = save(verif);
			commit();

			UpdateDataRes dataRes = new UpdateDataRes();
			dataRes.setVersion(updated.getVersion());

			result.setData(dataRes);
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());
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
		result.setMessage(MessageResponse.FAILED.getMessageResponse());
		try {
			begin();
			boolean isDeleted = verifDao.deleteById(id);
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
