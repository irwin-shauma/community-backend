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
import com.lawencon.community.dto.threadpollinglike.ThreadPollingLikeData;
import com.lawencon.community.dto.threadpollinglike.ThreadPollingLikeFindByIdRes;
import com.lawencon.community.dto.threadpollinglike.ThreadPollingLikeInsertReq;
import com.lawencon.community.dto.threadpollinglike.ThreadPollingLikeUpdateReq;
import com.lawencon.community.service.ThreadPollingLikeService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("thread-polling-likes")
public class ThreadPollingLikeController {

	@Autowired
	private ThreadPollingLikeService threadPollingLikeService;

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<ThreadPollingLikeData> result = threadPollingLikeService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("threads/{id}")
	public ResponseEntity<?> getAllByThread(@PathVariable("id") String id, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadPollingLikeData> result = threadPollingLikeService.findAllByThread(id, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		ThreadPollingLikeFindByIdRes result = threadPollingLikeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid ThreadPollingLikeInsertReq data) throws Exception {
		InsertRes result = threadPollingLikeService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid ThreadPollingLikeUpdateReq data) throws Exception {
		UpdateRes result = threadPollingLikeService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteRes result = threadPollingLikeService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("unlike/{id}")
	public ResponseEntity<DeleteRes> unlike(@PathVariable("id") String id) throws Exception {
		DeleteRes result = threadPollingLikeService.deleteByThreadAndUser(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}

}
