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
import com.lawencon.community.dto.threadheaderpolling.ThreadHeaderPollingData;
import com.lawencon.community.dto.threadheaderpolling.ThreadHeaderPollingFindByIdRes;
import com.lawencon.community.dto.threadheaderpolling.ThreadHeaderPollingInsertReq;
import com.lawencon.community.dto.threadheaderpolling.ThreadHeaderPollingUpdateReq;
import com.lawencon.community.service.ThreadHeaderPollingService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("thread-header-pollings")
public class ThreadHeaderPollingController {
	
	@Autowired
	private ThreadHeaderPollingService threadHeaderPollingService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<ThreadHeaderPollingData> result = threadHeaderPollingService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		ThreadHeaderPollingFindByIdRes result = threadHeaderPollingService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("users")
	public ResponseEntity<?> getByUserId(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadHeaderPollingData> result = threadHeaderPollingService.findByUserId(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid ThreadHeaderPollingInsertReq data) throws Exception {
		InsertRes result = threadHeaderPollingService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid ThreadHeaderPollingUpdateReq data) throws Exception {
		UpdateRes result = threadHeaderPollingService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
