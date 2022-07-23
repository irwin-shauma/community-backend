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
import com.lawencon.community.dto.balance.BalanceData;
import com.lawencon.community.dto.balance.BalanceFindByIdRes;
import com.lawencon.community.dto.balance.BalanceInsertReq;
import com.lawencon.community.dto.balance.BalanceUpdateReq;
import com.lawencon.community.service.BalanceService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("balances")
public class BalanceController {
	
	@Autowired
	private BalanceService balanceService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("query") String query, @RequestParam("startPage") Integer startPage,
			@RequestParam("maxPage") Integer maxPage) throws Exception {
		SearchQuery<BalanceData> result = balanceService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		BalanceFindByIdRes result = balanceService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody BalanceInsertReq data) throws Exception {
		InsertRes result = balanceService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody BalanceUpdateReq data) throws Exception {
		UpdateRes result = balanceService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}