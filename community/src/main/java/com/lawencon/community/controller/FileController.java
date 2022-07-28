package com.lawencon.community.controller;

<<<<<<< HEAD
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> d6b107f10294e8fbaa4fd4a8e12079a7af27abc2
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.lawencon.community.dto.file.FileInsertReq;
import com.lawencon.community.model.File;
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
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") String id) throws Exception {
        File file = fileService.getById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=attachment." + file.getFileExtension());

        byte[] fileInBytes = Base64.getDecoder().decode(file.getFileName());
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_OCTET_STREAM).body(fileInBytes);
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
