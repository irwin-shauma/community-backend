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
import com.lawencon.community.dto.eventtype.EventTypeData;
import com.lawencon.community.dto.eventtype.EventTypeFindByIdRes;
import com.lawencon.community.dto.eventtype.EventTypeInsertReq;
import com.lawencon.community.dto.eventtype.EventTypeUpdateReq;
import com.lawencon.community.service.EventTypeService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("event-types")
public class EventTypeController {
	
	@Autowired
	private EventTypeService eventTypeService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("query") String query, @RequestParam("startPage") Integer startPage,
			@RequestParam("maxPage") Integer maxPage) throws Exception {
		SearchQuery<EventTypeData> result = eventTypeService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		EventTypeFindByIdRes result = eventTypeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody EventTypeInsertReq data) throws Exception {
		InsertRes result = eventTypeService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody EventTypeUpdateReq data) throws Exception {
		UpdateRes result = eventTypeService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
