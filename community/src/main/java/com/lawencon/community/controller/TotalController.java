package com.lawencon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.TotalCountRes;
import com.lawencon.community.service.TotalService;

@RestController
@RequestMapping("counts")
public class TotalController {
	
	@Autowired
	private TotalService totalService;
	
	@GetMapping()
	public ResponseEntity<?> getTotal() throws Exception {
		TotalCountRes data = totalService.getAllCount();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
}