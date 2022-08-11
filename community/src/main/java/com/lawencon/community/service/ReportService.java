package com.lawencon.community.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.lawencon.community.dto.RevenueReport;

@Service
public class ReportService {
	
	public RevenueReport getReport() throws Exception {
		RevenueReport revenueReport = new RevenueReport();
		revenueReport.setFullName("Irwin");
		revenueReport.setBalance(new BigDecimal(5000));
		return revenueReport;
	}

}
