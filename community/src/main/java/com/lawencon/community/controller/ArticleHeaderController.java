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
import com.lawencon.community.dto.articleheader.ArticleHeaderData;
import com.lawencon.community.dto.articleheader.ArticleHeaderFindByIdRes;
import com.lawencon.community.dto.articleheader.ArticleHeaderInsertReq;
import com.lawencon.community.dto.articleheader.ArticleHeaderUpdateReq;
import com.lawencon.community.service.ArticleHeaderService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("article-headers")
public class ArticleHeaderController {

	@Autowired
	private ArticleHeaderService articleHeaderService;

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<ArticleHeaderData> result = articleHeaderService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		ArticleHeaderFindByIdRes result = articleHeaderService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid ArticleHeaderInsertReq data) throws Exception {
		InsertRes result = articleHeaderService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid ArticleHeaderUpdateReq data) throws Exception {
		UpdateRes result = articleHeaderService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteRes result = articleHeaderService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}

}
