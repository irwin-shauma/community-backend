package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.RegistrationReq;
import com.lawencon.community.dto.RegistrationRes;
import com.lawencon.community.dto.user.InsertCodeRes;
import com.lawencon.community.dto.verificaton.VerficationInsertReq;
import com.lawencon.community.service.VerificationService;

@RestController
@RequestMapping("verifications")
public class VerificationController {
	
	@Autowired
	private VerificationService verifService;
	
	@PostMapping
	public ResponseEntity<InsertCodeRes> insert(@RequestBody @Valid VerficationInsertReq email) throws Exception {
		InsertCodeRes result = verifService.insert(email);
		return new ResponseEntity<InsertCodeRes>(result, HttpStatus.OK);
	}
	
	
	@PostMapping("register")
	public ResponseEntity<RegistrationRes> register(@RequestBody @Valid RegistrationReq data) throws Exception {
		RegistrationRes result = verifService.register(data);
		return new ResponseEntity<RegistrationRes>(result, HttpStatus.BAD_REQUEST);
	}

}
