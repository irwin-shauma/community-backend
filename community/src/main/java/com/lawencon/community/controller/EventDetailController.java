package com.lawencon.community.controller;

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
import com.lawencon.community.dto.eventdetail.EventDetailData;
import com.lawencon.community.dto.eventdetail.EventDetailFindByIdRes;
import com.lawencon.community.dto.eventdetail.EventDetailInsertReq;
import com.lawencon.community.dto.eventdetail.EventDetailUpdateReq;
import com.lawencon.community.service.EventDetailService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("event-details")
public class EventDetailController {
	
	@Autowired
	private EventDetailService eventDetailService;

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<EventDetailData> result = eventDetailService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		EventDetailFindByIdRes result = eventDetailService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody EventDetailInsertReq data) throws Exception {
		InsertRes result = eventDetailService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody EventDetailUpdateReq data) throws Exception {
		UpdateRes result = eventDetailService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteRes result = eventDetailService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}


}
