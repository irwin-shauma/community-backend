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
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<ThreadTypeData> result = threadTypeService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		ThreadTypeFindByIdRes result = threadTypeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("regular")
	public ResponseEntity<?> getRegularType() throws Exception {
		ThreadTypeFindByIdRes result = threadTypeService.getRegular();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insertThreadType(@RequestBody @Valid ThreadTypeInsertReq data) throws Exception {
		InsertRes result = threadTypeService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> updateThreadType(@RequestBody @Valid ThreadTypeUpdateReq data) throws Exception {
		UpdateRes result = threadTypeService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> deleteThreadType(@PathVariable("id") String id) throws Exception {
		DeleteRes result = threadTypeService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}

}
