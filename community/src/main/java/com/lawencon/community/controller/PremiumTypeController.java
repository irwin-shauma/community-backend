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
import com.lawencon.community.dto.premiumtype.PremiumTypeData;
import com.lawencon.community.dto.premiumtype.PremiumTypeFindByIdRes;
import com.lawencon.community.dto.premiumtype.PremiumTypeInsertReq;
import com.lawencon.community.dto.premiumtype.PremiumTypeUpdateReq;
import com.lawencon.community.service.PremiumTypeService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("premium-types")
public class PremiumTypeController {
	@Autowired
	private PremiumTypeService premiumTypeService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query,
			@RequestParam(required = false) Integer startPage,
			@RequestParam(required = false) Integer maxPage) throws Exception {
		SearchQuery<PremiumTypeData> result = premiumTypeService.findAll(query, startPage, maxPage);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) throws Exception {
		PremiumTypeFindByIdRes result = premiumTypeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody PremiumTypeInsertReq data) throws Exception {
		InsertRes result = premiumTypeService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody PremiumTypeUpdateReq data) throws Exception {
		UpdateRes result = premiumTypeService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable("id") String id) throws Exception {
		DeleteRes result = premiumTypeService.deleteById(id);
		return new ResponseEntity<DeleteRes>(result, HttpStatus.OK);
	}
}
