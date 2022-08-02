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
import com.lawencon.community.dto.threaddetail.ThreadDetailData;
import com.lawencon.community.dto.threaddetail.ThreadDetailFindByIdRes;
import com.lawencon.community.dto.threaddetail.ThreadDetailInsertReq;
import com.lawencon.community.dto.threaddetail.ThreadDetailUpdateReq;
import com.lawencon.community.service.ThreadDetailService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("thread-details")
public class ThreadDetailController {

	@Autowired
	private ThreadDetailService threadDetailService;

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query, 
			@RequestParam(required = false)Integer startPage,
			@RequestParam(required = false)Integer maxPage) throws Exception {
		SearchQuery<ThreadDetailData> result = threadDetailService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		ThreadDetailFindByIdRes result = threadDetailService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid ThreadDetailInsertReq data) throws Exception {
		InsertRes result = threadDetailService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid ThreadDetailUpdateReq data) throws Exception {
		UpdateRes result = threadDetailService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
