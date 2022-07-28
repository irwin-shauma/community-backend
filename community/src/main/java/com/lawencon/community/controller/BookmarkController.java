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
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.bookmark.BookmarkData;
import com.lawencon.community.dto.bookmark.BookmarkFindByIdRes;
import com.lawencon.community.dto.bookmark.BookmarkInsertReq;
import com.lawencon.community.dto.bookmark.BookmarkUpdateReq;
import com.lawencon.community.service.BookmarkService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("bookmarks")
public class BookmarkController {
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@GetMapping
	public ResponseEntity<?> findAllByUser(Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<BookmarkData> result = bookmarkService.findAllByUser(startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		BookmarkFindByIdRes result = bookmarkService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody BookmarkInsertReq data) throws Exception {
		InsertRes result = bookmarkService.insert(data);
		return new ResponseEntity<InsertRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody BookmarkUpdateReq data) throws Exception {
		UpdateRes result = bookmarkService.update(data);
		return new ResponseEntity<UpdateRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteRes result = bookmarkService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}

}
