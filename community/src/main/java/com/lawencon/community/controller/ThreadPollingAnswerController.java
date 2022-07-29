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
import com.lawencon.community.dto.threadpollinganswer.ThreadPollingAnswerData;
import com.lawencon.community.dto.threadpollinganswer.ThreadPollingAnswerFindByIdRes;
import com.lawencon.community.dto.threadpollinganswer.ThreadPollingAnswerInsertReq;
import com.lawencon.community.dto.threadpollinganswer.ThreadPollingAnswerUpdateReq;
import com.lawencon.community.service.ThreadPollingAnswerService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("thread-polling-answers")
public class ThreadPollingAnswerController {
	
	@Autowired
	private ThreadPollingAnswerService threadPollingAnswerService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query, 
			@RequestParam(required = false)Integer startPage,
			@RequestParam(required = false)Integer maxPage) throws Exception {
		SearchQuery<ThreadPollingAnswerData> result = threadPollingAnswerService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		ThreadPollingAnswerFindByIdRes result = threadPollingAnswerService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody ThreadPollingAnswerInsertReq data) throws Exception {
		InsertRes result = threadPollingAnswerService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody ThreadPollingAnswerUpdateReq data) throws Exception {
		UpdateRes result = threadPollingAnswerService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
