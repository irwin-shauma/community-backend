package com.lawencon.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.role.RoleData;
import com.lawencon.community.service.RoleService;
import com.lawencon.model.SearchQuery;
import com.lawencon.util.JasperUtil;

@RestController
@RequestMapping("reports")
public class ReportController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JasperUtil jasperUtil;
	
	@GetMapping("role")
	public ResponseEntity<?> reportSample() throws Exception {
		SearchQuery<RoleData> listData = roleService.findAll(null, null, null);
		
		List<RoleData> roles = listData.getData();
		
		Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Communify Sejahtera");
		
		byte[] out = jasperUtil.responseToByteArray(roles, map, "sample");
		
		String fileName = "roles_report.pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(out);
	}
}
