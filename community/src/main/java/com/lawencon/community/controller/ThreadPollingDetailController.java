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
import com.lawencon.community.dto.threadpollingdetail.ThreadPollingDetailData;
import com.lawencon.community.dto.threadpollingdetail.ThreadPollingDetailFindByIdRes;
import com.lawencon.community.dto.threadpollingdetail.ThreadPollingDetailInsertReq;
import com.lawencon.community.dto.threadpollingdetail.ThreadPollingDetailUpdateReq;
import com.lawencon.community.service.ThreadPollingDetailService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("thread-pollings")
public class ThreadPollingDetailController {
	
	@Autowired
	private ThreadPollingDetailService threadPollingDetailService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("query") String query, @RequestParam("startPage") Integer startPage,
			@RequestParam("maxPage") Integer maxPage) throws Exception {
		SearchQuery<ThreadPollingDetailData> result = threadPollingDetailService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		ThreadPollingDetailFindByIdRes result = threadPollingDetailService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid ThreadPollingDetailInsertReq data) throws Exception {
		InsertRes result = threadPollingDetailService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid ThreadPollingDetailUpdateReq data) throws Exception {
		UpdateRes result = threadPollingDetailService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
