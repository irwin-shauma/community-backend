package com.lawencon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.payment.PaymentData;
import com.lawencon.community.dto.payment.PaymentFindByIdRes;
import com.lawencon.community.dto.payment.PaymentInsertReq;
import com.lawencon.community.dto.payment.PaymentUpdateReq;
import com.lawencon.community.service.PaymentService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("query") String query, @RequestParam("startPage") Integer startPage,
			@RequestParam("maxPage") Integer maxPage) throws Exception {
		SearchQuery<PaymentData> result = paymentService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		PaymentFindByIdRes result = paymentService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody PaymentInsertReq data) throws Exception {
		InsertRes result = paymentService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody PaymentUpdateReq data) throws Exception {
		UpdateRes result = paymentService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
