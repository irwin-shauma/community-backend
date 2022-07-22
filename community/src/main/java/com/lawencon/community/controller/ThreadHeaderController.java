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
import com.lawencon.community.dto.threadheader.ThreadHeaderData;
import com.lawencon.community.dto.threadheader.ThreadHeaderFindByIdRes;
import com.lawencon.community.dto.threadheader.ThreadHeaderInsertReq;
import com.lawencon.community.dto.threadheader.ThreadHeaderUpdateReq;
import com.lawencon.community.service.ThreadHeaderService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("thread-headers")
public class ThreadHeaderController {

	@Autowired
	private ThreadHeaderService threadHeaderService;

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("query") String query, @RequestParam("startPage") Integer startPage,
			@RequestParam("maxPage") Integer maxPage) throws Exception {
		SearchQuery<ThreadHeaderData> result = threadHeaderService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		ThreadHeaderFindByIdRes result = threadHeaderService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody ThreadHeaderInsertReq data) throws Exception {
		InsertRes result = threadHeaderService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody ThreadHeaderUpdateReq data) throws Exception {
		UpdateRes result = threadHeaderService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
