package com.lawencon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.RegistrationReq;
import com.lawencon.community.dto.RegistrationRes;
import com.lawencon.community.dto.verificaton.VerficationInsertReq;
import com.lawencon.community.dto.verificaton.VerificationData;
import com.lawencon.community.dto.verificaton.VerificationFindByIdRes;
import com.lawencon.community.service.VerificationService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("verifications")
public class VerificationController {
	
	@Autowired
	private VerificationService verifService;
	
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<VerificationData> result = verifService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		VerificationFindByIdRes result = verifService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody VerficationInsertReq email) throws Exception {
		InsertRes result = verifService.insert(email);
		return new ResponseEntity<InsertRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteRes result = verifService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}
	
	@PostMapping("register")
	public ResponseEntity<RegistrationRes> insert(@RequestBody RegistrationReq data) throws Exception {
		RegistrationRes result = verifService.register(data);
		return new ResponseEntity<RegistrationRes>(result, HttpStatus.OK);
	}

}
