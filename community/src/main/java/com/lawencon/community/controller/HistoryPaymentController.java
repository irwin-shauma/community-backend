package com.lawencon.community.controller;

import javax.validation.Valid;

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
import com.lawencon.community.dto.historypayment.HistoryPaymentData;
import com.lawencon.community.dto.historypayment.HistoryPaymentFindByIdRes;
import com.lawencon.community.dto.historypayment.HistoryPaymentInsertReq;
import com.lawencon.community.dto.historypayment.HistoryPaymentUpdateReq;
import com.lawencon.community.service.HistoryPaymentService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("history-payments")
public class HistoryPaymentController {
	
	@Autowired
	private HistoryPaymentService historyPaymentService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("query") String query, @RequestParam("startPage") Integer startPage,
			@RequestParam("maxPage") Integer maxPage) throws Exception {
		SearchQuery<HistoryPaymentData> result = historyPaymentService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		HistoryPaymentFindByIdRes result = historyPaymentService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid HistoryPaymentInsertReq data) throws Exception {
		InsertRes result = historyPaymentService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid HistoryPaymentUpdateReq data) throws Exception {
		UpdateRes result = historyPaymentService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


}
