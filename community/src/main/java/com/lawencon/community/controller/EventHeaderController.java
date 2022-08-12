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
import com.lawencon.community.dto.eventheader.EventHeaderData;
import com.lawencon.community.dto.eventheader.EventHeaderFindByIdRes;
import com.lawencon.community.dto.eventheader.EventHeaderInsertReq;
import com.lawencon.community.dto.eventheader.EventHeaderUpdateReq;
import com.lawencon.community.service.EventHeaderService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("event-headers")
public class EventHeaderController {
	@Autowired
	private EventHeaderService eventHeaderService;

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage, @RequestParam(required = false) Integer maxPage)
			throws Exception {
		SearchQuery<EventHeaderData> result = eventHeaderService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("users")
	public ResponseEntity<?> getAllByUser(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage, @RequestParam(required = false) Integer maxPage)
			throws Exception {
		SearchQuery<EventHeaderData> result = eventHeaderService.findAllUser(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("events")
	public ResponseEntity<?> getAllEvent(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage, @RequestParam(required = false) Integer maxPage)
			throws Exception {
		SearchQuery<EventHeaderData> result = eventHeaderService.findAllEvent(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("course")
	public ResponseEntity<?> getAllCourse(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage, @RequestParam(required = false) Integer maxPage)
			throws Exception {
		SearchQuery<EventHeaderData> result = eventHeaderService.findAllCourse(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		EventHeaderFindByIdRes result = eventHeaderService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid EventHeaderInsertReq data) throws Exception {
		InsertRes result = eventHeaderService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid EventHeaderUpdateReq data) throws Exception {
		UpdateRes result = eventHeaderService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteRes result = eventHeaderService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}
	
}
