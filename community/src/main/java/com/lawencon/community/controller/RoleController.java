package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.lawencon.community.dto.role.RoleData;
import com.lawencon.community.dto.role.RoleFindByIdRes;
import com.lawencon.community.dto.role.RoleInsertReq;
import com.lawencon.community.dto.role.RoleUpdateReq;
import com.lawencon.community.service.RoleService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PreAuthorize("hasAuthority('SYSTEM')")
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query, 
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<RoleData> mhs = roleService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(mhs, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getMhs(@PathVariable("id") String id) throws Exception {
		RoleFindByIdRes mhs = roleService.getById(id);
		return new ResponseEntity<>(mhs, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid RoleInsertReq data) throws Exception {
		InsertRes result = roleService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody @Valid RoleUpdateReq data) throws Exception {
		UpdateRes result = roleService.update(data);
		return new ResponseEntity<UpdateRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteRes result = roleService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}

}
