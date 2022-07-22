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
import com.lawencon.community.dto.threadtype.ThreadTypeData;
import com.lawencon.community.dto.threadtype.ThreadTypeFindByIdRes;
import com.lawencon.community.dto.threadtype.ThreadTypeInsertReq;
import com.lawencon.community.dto.threadtype.ThreadTypeUpdateReq;
import com.lawencon.community.service.ThreadTypeService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("thread-types")
public class ThreadTypeController {

	@Autowired
	private ThreadTypeService threadTypeService;

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("query") String query, @RequestParam("startPage") Integer startPage,
			@RequestParam("maxPage") Integer maxPage) throws Exception {
		SearchQuery<ThreadTypeData> result = threadTypeService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		ThreadTypeFindByIdRes result = threadTypeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody ThreadTypeInsertReq data) throws Exception {
		InsertRes result = threadTypeService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody ThreadTypeUpdateReq data) throws Exception {
		UpdateRes result = threadTypeService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
