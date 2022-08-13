package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.eventpaymenthistory.EventPaymentHistoryData;
import com.lawencon.community.dto.eventpaymenthistory.EventPaymentHistoryFindByIdRes;
import com.lawencon.community.dto.eventpaymenthistory.EventPaymentHistoryInsertReq;
import com.lawencon.community.dto.eventpaymenthistory.EventPaymentHistoryUpdateReq;
import com.lawencon.community.service.EventPaymentHistoryService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("event-payment-histories")
public class EventPaymentHistoryController {
	
	@Autowired
	private EventPaymentHistoryService eventPaymentHistoryService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<EventPaymentHistoryData> result = eventPaymentHistoryService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("unapprove")
	public ResponseEntity<?> getAllUnapprove(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<EventPaymentHistoryData> result = eventPaymentHistoryService.findAllUnapprove(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("users")
	public ResponseEntity<?> getAllByUsers(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<EventPaymentHistoryData> result = eventPaymentHistoryService.findAllByUser(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		EventPaymentHistoryFindByIdRes result = eventPaymentHistoryService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid EventPaymentHistoryInsertReq data) throws Exception {
		InsertRes result = eventPaymentHistoryService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid EventPaymentHistoryUpdateReq data) throws Exception {
		UpdateRes result = eventPaymentHistoryService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteRes result = eventPaymentHistoryService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}

}
