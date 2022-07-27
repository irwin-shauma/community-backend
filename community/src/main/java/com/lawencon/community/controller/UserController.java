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
import com.lawencon.community.dto.user.UpdatePhotoProfileReq;
import com.lawencon.community.dto.user.UserData;
import com.lawencon.community.dto.user.UserFindByIdRes;
import com.lawencon.community.dto.user.UserInsertReq;
import com.lawencon.community.dto.user.UserUpdateReq;
import com.lawencon.community.service.UserService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> showAll(@RequestParam(required = false) String query, 
			@RequestParam(required = false)Integer startPage,
			@RequestParam(required = false)Integer maxPage) throws Exception {
		SearchQuery<UserData> data = userService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		UserFindByIdRes user = userService.getById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody UserInsertReq user) throws Exception {
		InsertRes data = userService.insert(user);
		return new ResponseEntity<InsertRes>(data, HttpStatus.CREATED);
	}
	
	@PutMapping 
	public ResponseEntity<UpdateRes> update(@RequestBody UserUpdateReq user) throws Exception {
		UpdateRes data = userService.update(user);
		return new ResponseEntity<UpdateRes>(data, HttpStatus.OK);
	}
	
	@PutMapping("profile-pictures")
	public ResponseEntity<UpdateRes> updateProfilePic(@RequestBody UpdatePhotoProfileReq user) throws Exception {
		UpdateRes data = userService.updateProfilePic(user);
		return new ResponseEntity<UpdateRes>(data, HttpStatus.OK);
	}

}
