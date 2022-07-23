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
import com.lawencon.community.dto.premiumpaymenthistory.PremiumPaymentHistoryData;
import com.lawencon.community.dto.premiumpaymenthistory.PremiumPaymentHistoryFindByIdRes;
import com.lawencon.community.dto.premiumpaymenthistory.PremiumPaymentHistoryInsertReq;
import com.lawencon.community.dto.premiumpaymenthistory.PremiumPaymentHistoryUpdateReq;
import com.lawencon.community.service.PremiumPaymentHistoryService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("premium-payment-histories")
public class PremiumPaymentHistoryController {

	@Autowired
	private PremiumPaymentHistoryService premiumPaymentHistoryService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("query") String query, @RequestParam("startPage") Integer startPage,
			@RequestParam("maxPage") Integer maxPage) throws Exception {
		SearchQuery<PremiumPaymentHistoryData> result = premiumPaymentHistoryService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		PremiumPaymentHistoryFindByIdRes result = premiumPaymentHistoryService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody PremiumPaymentHistoryInsertReq data) throws Exception {
		InsertRes result = premiumPaymentHistoryService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody PremiumPaymentHistoryUpdateReq data) throws Exception {
		UpdateRes result = premiumPaymentHistoryService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
