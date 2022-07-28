package com.lawencon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.file.FileData;
import com.lawencon.community.dto.file.FileFindByIdRes;
import com.lawencon.community.dto.file.FileInsertReq;
import com.lawencon.community.service.FileService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("files")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("query") String query, @RequestParam("startPage") Integer startPage,
			@RequestParam("maxPage") Integer maxPage) throws Exception {
		SearchQuery<FileData> result = fileService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		FileFindByIdRes result = fileService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody FileInsertReq data) throws Exception {
		InsertRes result = fileService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteRes result = fileService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}

}
