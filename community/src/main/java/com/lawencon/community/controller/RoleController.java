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

import com.lawencon.community.model.Role;
import com.lawencon.community.service.RoleService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("query") String query, 
			@RequestParam("startPage") Integer startPage,
			@RequestParam("maxPage") Integer maxPage) throws Exception {
		SearchQuery<Role> mhs = roleService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(mhs, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getMhs(@PathVariable("id") String id) throws Exception {
		Role mhs = roleService.getById(id);
		return new ResponseEntity<>(mhs, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Role data) throws Exception {
		roleService.insert(data);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Role data) throws Exception {
		roleService.update(data);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	

}
