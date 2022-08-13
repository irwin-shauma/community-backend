package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.EventDetailDao;
import com.lawencon.community.dao.EventHeaderDao;
import com.lawencon.community.dao.EventPaymentHistoryDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.PaymentDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.eventpaymenthistory.EventPaymentHistoryData;
import com.lawencon.community.dto.eventpaymenthistory.EventPaymentHistoryFindByIdRes;
import com.lawencon.community.dto.eventpaymenthistory.EventPaymentHistoryInsertReq;
import com.lawencon.community.dto.eventpaymenthistory.EventPaymentHistoryUpdateReq;
import com.lawencon.community.model.EventDetail;
import com.lawencon.community.model.EventHeader;
import com.lawencon.community.model.EventPaymentHistory;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Payment;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class EventPaymentHistoryService extends BaseCoreService<EventPaymentHistory> {

	@Autowired
	private EventPaymentHistoryDao eventPaymentHistoryDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EventHeaderDao eventHeaderDao;

	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private EventDetailDao eventDetailDao;

	public InsertRes insert(EventPaymentHistoryInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		String payCode = RandomStringUtils.randomAlphanumeric(5);
		String trxNo = RandomStringUtils.randomAlphanumeric(5);
		try {
			EventPaymentHistory eventPaymentHistory = new EventPaymentHistory();
			eventPaymentHistory.setEventPaymentCode(code);
			
			User user = userDao.getById(getAuthPrincipal());
			eventPaymentHistory.setUser(user);

			Payment payment = new Payment();

			File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());
			
			begin();
			File insertedFile = fileDao.save(file);

			payment.setPaymentCode(payCode);

			User users = new User();
			users.setId(getAuthPrincipal());
			payment.setUser(users);
			payment.setFile(insertedFile);
			payment.setIsActive(true);
			payment.setIsApprove(false);
			payment.setCreatedBy(getAuthPrincipal());

			Payment insertedPayment = paymentDao.save(payment);

			EventHeader eventHeader = eventHeaderDao.getById(data.getEventHeaderId());
			eventPaymentHistory.setEventHeader(eventHeader);
			eventPaymentHistory.setPayment(insertedPayment);
			eventPaymentHistory.setTrxNo(trxNo);
			eventPaymentHistory.setIsActive(false);

			EventPaymentHistory eventPaymentHistoryInsert = save(eventPaymentHistory);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(eventPaymentHistoryInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public UpdateRes update(EventPaymentHistoryUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			EventPaymentHistory eventPaymentHistoryDb = eventPaymentHistoryDao.getById(data.getId());

			Payment payment = paymentDao.getById(data.getPaymentId());
			payment.setIsApprove(data.getIsApprove());

			eventPaymentHistoryDb.setIsActive(data.getIsActive());

			begin();
			paymentDao.save(payment);
			EventPaymentHistory eventPaymentHistoryUpdate = eventPaymentHistoryDao.save(eventPaymentHistoryDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(eventPaymentHistoryUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	public EventPaymentHistoryFindByIdRes getById(String id) throws Exception {
		EventPaymentHistory eventPaymentHistoryDb = eventPaymentHistoryDao.getById(id);

		EventPaymentHistoryData data = new EventPaymentHistoryData();
		data.setId(eventPaymentHistoryDb.getId());
		data.setUserId(eventPaymentHistoryDb.getUser().getId());
		data.setEventHeaderId(eventPaymentHistoryDb.getEventHeader().getId());
		data.setPaymentId(eventPaymentHistoryDb.getPayment().getId());
		data.setTrxNo(eventPaymentHistoryDb.getTrxNo());
		data.setIsActive(eventPaymentHistoryDb.getIsActive());
		data.setVersion(eventPaymentHistoryDb.getVersion());

		EventPaymentHistoryFindByIdRes result = new EventPaymentHistoryFindByIdRes();
		result.setData(data);

		return result;

	}

	public SearchQuery<EventPaymentHistoryData> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<EventPaymentHistory> dataDb = eventPaymentHistoryDao.findAll(query, startPage, maxPage);

		List<EventPaymentHistoryData> eventPaymentHistoryDataList = new ArrayList<EventPaymentHistoryData>();

		dataDb.getData().forEach(eventPaymentHistory -> {
			EventPaymentHistoryData data = new EventPaymentHistoryData();
			data.setId(eventPaymentHistory.getId());
			data.setEventPaymentHistoryCode(eventPaymentHistory.getEventPaymentCode());
			data.setUserId(eventPaymentHistory.getUser().getId());
			data.setEventHeaderId(eventPaymentHistory.getEventHeader().getId());
			data.setPaymentId(eventPaymentHistory.getPayment().getId());

			User userDb = userDao.getById(eventPaymentHistory.getUser().getId());
			data.setEmail(userDb.getEmail());
			data.setFullname(userDb.getProfile().getFullName());

			EventHeader eventHeaderDb = eventHeaderDao.getById(eventPaymentHistory.getEventHeader().getId());
			data.setTitle(eventHeaderDb.getTitle());
			data.setEventCreator(eventHeaderDb.getUser().getId());
			
			try {
				EventDetail eventDetail = eventDetailDao.findByHeader(eventHeaderDb.getId());
				data.setPrice(eventDetail.getPrice());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Payment payment = paymentDao.getById(eventPaymentHistory.getPayment().getId());
			data.setIsAprove(payment.getIsApprove());
			data.setFileId(payment.getFile().getId());

			data.setTrxNo(eventPaymentHistory.getTrxNo());
			data.setIsActive(eventPaymentHistory.getIsActive());
			data.setVersion(eventPaymentHistory.getVersion());

			eventPaymentHistoryDataList.add(data);
		});

		SearchQuery<EventPaymentHistoryData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(eventPaymentHistoryDataList);

		return result;
	}
	
	public SearchQuery<EventPaymentHistoryData> findAllUnapprove(String query, Integer startPage, Integer maxPage)
			throws Exception {
		List<EventPaymentHistory> dataDb = eventPaymentHistoryDao.findAllUnapprove(query, startPage, maxPage);

		List<EventPaymentHistoryData> eventPaymentHistoryDataList = new ArrayList<EventPaymentHistoryData>();

		dataDb.forEach(eventPaymentHistory -> {
			EventPaymentHistoryData data = new EventPaymentHistoryData();
			data.setId(eventPaymentHistory.getId());
			data.setEventPaymentHistoryCode(eventPaymentHistory.getEventPaymentCode());
			data.setUserId(eventPaymentHistory.getUser().getId());
			data.setEventHeaderId(eventPaymentHistory.getEventHeader().getId());
			data.setPaymentId(eventPaymentHistory.getPayment().getId());

			User userDb = userDao.getById(eventPaymentHistory.getUser().getId());
			data.setEmail(userDb.getEmail());
			data.setFullname(userDb.getProfile().getFullName());

			EventHeader eventHeaderDb = eventHeaderDao.getById(eventPaymentHistory.getEventHeader().getId());
			data.setTitle(eventHeaderDb.getTitle());
			data.setEventCreator(eventHeaderDb.getUser().getId());
			
			try {
				EventDetail eventDetail = eventDetailDao.findByHeader(eventHeaderDb.getId());
				data.setPrice(eventDetail.getPrice());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Payment payment = paymentDao.getById(eventPaymentHistory.getPayment().getId());
			data.setIsAprove(payment.getIsApprove());
			data.setFileId(payment.getFile().getId());

			data.setTrxNo(eventPaymentHistory.getTrxNo());
			data.setIsActive(eventPaymentHistory.getIsActive());
			data.setVersion(eventPaymentHistory.getVersion());

			eventPaymentHistoryDataList.add(data);
		});

		SearchQuery<EventPaymentHistoryData> result = new SearchQuery<>();
		int count = eventPaymentHistoryDao.countAll().intValue();
		result.setCount(count);
		result.setData(eventPaymentHistoryDataList);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = eventPaymentHistoryDao.deleteById(id);
			commit();

			if (isDeleted) {
				result.setMessage(MessageResponse.DELETED.getMessageResponse());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

}
