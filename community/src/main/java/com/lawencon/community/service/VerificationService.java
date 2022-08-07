package com.lawencon.community.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dto.RegistrationReq;
import com.lawencon.community.dto.RegistrationRes;
import com.lawencon.community.dto.user.InsertCodeRes;
import com.lawencon.community.dto.verificaton.VerficationInsertReq;
import com.lawencon.community.pojo.Verification;
import com.lawencon.community.util.EmailComponent;
import com.lawencon.util.VerificationCodeUtil;

@Service
public class VerificationService extends BaseCoreService<Verification> {


	@Autowired
	private EmailComponent emailComponent;
	
	@Autowired
	private VerificationCodeUtil verificationCodeUtil;
	

	public InsertCodeRes insert(VerficationInsertReq email) throws Exception {
		InsertCodeRes result = new InsertCodeRes();
		try {
			String verification = RandomStringUtils.randomAlphanumeric(5);
			verificationCodeUtil.addVerificationCode(email.getEmail(), verification);
			Map<String, Object> template = new HashMap<String, Object>();
			template.put("code", verification);

			new Thread(() -> {
				try {
					emailComponent.sendMessageUsingFreemarkerTemplate(email.getEmail(), "Verification Code", template);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();

			result.setStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;

	}
	
	public RegistrationRes register(RegistrationReq data) throws Exception {
		RegistrationRes response = new RegistrationRes();
		response.setData(true);
			verificationCodeUtil.validateVerificationCode(data.getEmail(), data.getVerificationCode());
		return response;
	}
	
}
