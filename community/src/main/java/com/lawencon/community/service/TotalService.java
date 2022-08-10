package com.lawencon.community.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ArticleHeaderDao;
import com.lawencon.community.dao.BalanceDao;
import com.lawencon.community.dao.EventHeaderDao;
import com.lawencon.community.dao.PremiumPaymentHistoryDao;
import com.lawencon.community.dao.ThreadHeaderDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.TotalCountData;
import com.lawencon.community.dto.TotalCountRes;

@Service
public class TotalService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ArticleHeaderDao articleHeaderDao;
	
	@Autowired
	private ThreadHeaderDao threadHeaderDao;
	
	@Autowired
	private EventHeaderDao eventHeaderDao;
	
	@Autowired
	private PremiumPaymentHistoryDao premiumPaymentHistoryDao;
	
	@Autowired
	private BalanceDao balanceDao;
	
	public TotalCountRes getAllCount() throws Exception {
		TotalCountRes totalCountRes = new TotalCountRes();
		TotalCountData totalCountData = new TotalCountData();
		
		Long totalUser = userDao.countAll();
		totalCountData.setTotalUser(totalUser);
		
		Long totalPremiumUser = premiumPaymentHistoryDao.countAllPremiumUser();
		totalCountData.setTotalPremiumUser(totalPremiumUser);
		
		Long totalArticle = articleHeaderDao.countAll();
		totalCountData.setTotalArticle(totalArticle);
		
		Long totalThread = threadHeaderDao.countAll();
		totalCountData.setTotalThread(totalThread);
		
		Long totalEvent = eventHeaderDao.countAllEvent("Event");
		totalCountData.setTotalEvent(totalEvent);
		
		Long totalCourse = eventHeaderDao.countAllEvent("Course");
		totalCountData.setTotalCourse(totalCourse);
		
		BigDecimal systemBalance = balanceDao.findSystemBalance();
		totalCountData.setTotalRevenue(systemBalance);
		
		totalCountRes.setData(totalCountData);
		
		return totalCountRes;
	}

}
