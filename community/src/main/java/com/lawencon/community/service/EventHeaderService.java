package com.lawencon.community.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.EventTypeConstant;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.EventDetailDao;
import com.lawencon.community.dao.EventHeaderDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.eventheader.EventHeaderData;
import com.lawencon.community.dto.eventheader.EventHeaderFindByIdRes;
import com.lawencon.community.dto.eventheader.EventHeaderInsertReq;
import com.lawencon.community.dto.eventheader.EventHeaderUpdateReq;
import com.lawencon.community.model.EventDetail;
import com.lawencon.community.model.EventHeader;
import com.lawencon.community.model.EventType;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class EventHeaderService extends BaseCoreService<EventHeader> {

	@Autowired
	private EventHeaderDao eventHeaderDao;

	@Autowired
	private EventDetailDao eventDetailDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private FileDao fileDao;

	public InsertRes insert(EventHeaderInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		String detailCode = RandomStringUtils.randomAlphanumeric(5);
		try {
			begin();
			EventHeader eventHeader = new EventHeader();
			eventHeader.setEventHeaderCode(code);
			eventHeader.setTitle(data.getTitle());

			EventType eventType = new EventType();
			eventType.setId(data.getEventTypeId());
			eventHeader.setEventType(eventType);

			if (data.getFileName() != null) {
				File file = new File();
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				fileDao.save(file);
				eventHeader.setFile(file);
			}
			User user = new User();
			user.setId(getAuthPrincipal());
			eventHeader.setUser(user);
			eventHeader.setIsActive(true);

			EventHeader eventHeaderInsert = save(eventHeader);

			EventDetail eventDetail = new EventDetail();
			eventDetail.setEventDetailCode(detailCode);
			eventDetail.setEventHeader(eventHeaderInsert);

			eventDetail.setPrice(data.getPrice());
			eventDetail.setStartDate(stringToLocalDateTime(data.getStarts()));
			eventDetail.setEndDate(stringToLocalDateTime(data.getEnds()));
			eventDetail.setProvider(data.getProvider());
			eventDetail.setLocations(data.getLocation());
			eventDetail.setCreatedBy(getAuthPrincipal());
			eventDetail.setIsActive(true);

			eventDetailDao.save(eventDetail);

			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(eventHeaderInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public UpdateRes update(EventHeaderUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			EventHeader eventHeaderDb = eventHeaderDao.getById(data.getId());
			eventHeaderDb.setTitle(data.getTitle());

			begin();
			if (data.getFileName() != null) {
				File newFile = new File();
				newFile.setFileName(data.getFileName());
				newFile.setFileExtension(data.getFileExtension());
				File insertFile = fileDao.save(newFile);
				eventHeaderDb.setFile(insertFile);
			} else {
				File oldFile = fileDao.getById(eventHeaderDb.getFile().getId());
				eventHeaderDb.setFile(oldFile);
			}

			EventHeader eventHeaderUpdate = save(eventHeaderDb);

			EventDetail eventDetail = eventDetailDao.findByHeader(data.getId());
			eventDetail.setPrice(data.getPrice());
			eventDetail.setStartDate(stringToLocalDateTime(data.getStarts()));
			eventDetail.setEndDate(stringToLocalDateTime(data.getEnds()));
			eventDetail.setProvider(data.getProvider());
			eventDetail.setLocations(data.getLocation());
			eventDetail.setCreatedBy(getAuthPrincipal());

			eventDetailDao.save(eventDetail);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(eventHeaderUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	public EventHeaderFindByIdRes getById(String id) throws Exception {
		EventHeader eventHeaderDb = eventHeaderDao.getById(id);

		EventHeaderData data = new EventHeaderData();
		data.setId(eventHeaderDb.getId());
		data.setEventHeaderCode(eventHeaderDb.getEventHeaderCode());
		data.setTitle(eventHeaderDb.getTitle());
		data.setEventTypeId(eventHeaderDb.getEventType().getId());
		User user = userDao.getById(eventHeaderDb.getCreatedBy());
		data.setUserId(user.getId());

		Profile profile = profileDao.getById(user.getProfile().getId());
		data.setFulName(profile.getFullName());

		if (eventHeaderDb.getFile() != null) {
			data.setFileId(eventHeaderDb.getFile().getId());
		}

		data.setIsActive(eventHeaderDb.getIsActive());
		data.setVersion(eventHeaderDb.getVersion());

		EventDetail eventDetail = eventDetailDao.findByHeader(eventHeaderDb.getId());
		if (eventDetail != null) {
			data.setPrice(eventDetail.getPrice());
			data.setStartDate(eventDetail.getStartDate());
			data.setEndDate(eventDetail.getEndDate());
			data.setProvider(eventDetail.getProvider());
			data.setLocation(eventDetail.getLocations());
		}

		EventHeaderFindByIdRes result = new EventHeaderFindByIdRes();
		result.setData(data);

		return result;
	}

	public SearchQuery<EventHeaderData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<EventHeader> dataDb = eventHeaderDao.findAll(query, startPage, maxPage);

		List<EventHeaderData> eventHeaderDataList = new ArrayList<EventHeaderData>();

		dataDb.getData().forEach(eventHeader -> {
			EventHeaderData data = new EventHeaderData();
			data.setId(eventHeader.getId());
			data.setEventHeaderCode(eventHeader.getEventHeaderCode());

			data.setTitle(eventHeader.getTitle());
			data.setEventTypeId(eventHeader.getEventType().getId());

			User user = userDao.getById(eventHeader.getCreatedBy());
			Profile profile = profileDao.getById(user.getProfile().getId());
			data.setFulName(profile.getFullName());
			if (eventHeader.getFile() != null) {
				data.setFileId(eventHeader.getFile().getId());
			}

			data.setIsActive(eventHeader.getIsActive());
			data.setVersion(eventHeader.getVersion());

			try {
				EventDetail eventDetail = eventDetailDao.findByHeader(eventHeader.getId());
				if (eventDetail != null) {
					data.setPrice(eventDetail.getPrice());
					data.setStartDate(eventDetail.getStartDate());
					data.setEndDate(eventDetail.getEndDate());
					data.setProvider(eventDetail.getProvider());
					data.setLocation(eventDetail.getLocations());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			eventHeaderDataList.add(data);
		});

		SearchQuery<EventHeaderData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(eventHeaderDataList);

		return result;
	}

	public SearchQuery<EventHeaderData> findAllUser(String query, Integer startPage, Integer maxPage) throws Exception {
		List<EventHeader> dataDb = eventHeaderDao.findAllByUser(getAuthPrincipal(), query, startPage, maxPage);

		List<EventHeaderData> eventHeaderDataList = new ArrayList<EventHeaderData>();

		dataDb.forEach(eventHeader -> {
			EventHeaderData data = new EventHeaderData();
			data.setId(eventHeader.getId());
			data.setEventHeaderCode(eventHeader.getEventHeaderCode());

			data.setTitle(eventHeader.getTitle());
			data.setEventTypeId(eventHeader.getEventType().getId());

			User user = userDao.getById(eventHeader.getCreatedBy());
			if (eventHeader.getUser() != null) {
				data.setUserId(eventHeader.getUser().getId());
			}

			Profile profile = profileDao.getById(user.getProfile().getId());
			data.setFulName(profile.getFullName());
			data.setUserId(eventHeader.getUser().getId());
			
			
			if (eventHeader.getFile() != null) {
				data.setFileId(eventHeader.getFile().getId());
			}

			data.setIsActive(eventHeader.getIsActive());
			data.setVersion(eventHeader.getVersion());

			try {
				EventDetail eventDetail = eventDetailDao.findByHeader(eventHeader.getId());
				if (eventDetail != null) {
					data.setPrice(eventDetail.getPrice());
					data.setStartDate(eventDetail.getStartDate());
					data.setEndDate(eventDetail.getEndDate());
					data.setProvider(eventDetail.getProvider());
					data.setLocation(eventDetail.getLocations());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			eventHeaderDataList.add(data);
		});

		SearchQuery<EventHeaderData> result = new SearchQuery<>();
		result.setData(eventHeaderDataList);

		return result;
	}

	public SearchQuery<EventHeaderData> findAllEvent(String query, Integer startPage, Integer maxPage)
			throws Exception {
		List<EventHeader> dataDb = eventHeaderDao.findAllByType(EventTypeConstant.EVENT.name(), query, startPage,
				maxPage);

		List<EventHeaderData> eventHeaderDataList = new ArrayList<EventHeaderData>();

		dataDb.forEach(eventHeader -> {
			EventHeaderData data = new EventHeaderData();
			data.setId(eventHeader.getId());
			data.setEventHeaderCode(eventHeader.getEventHeaderCode());

			data.setTitle(eventHeader.getTitle());
			data.setEventTypeId(eventHeader.getEventType().getId());

			User user = userDao.getById(eventHeader.getCreatedBy());
			Profile profile = profileDao.getById(user.getProfile().getId());
			data.setFulName(profile.getFullName());
			if (eventHeader.getFile() != null) {
				data.setFileId(eventHeader.getFile().getId());
			}

			data.setIsActive(eventHeader.getIsActive());
			data.setVersion(eventHeader.getVersion());

			try {
				EventDetail eventDetail = eventDetailDao.findByHeader(eventHeader.getId());
				if (eventDetail != null) {
					data.setPrice(eventDetail.getPrice());
					data.setStartDate(eventDetail.getStartDate());
					data.setEndDate(eventDetail.getEndDate());
					data.setProvider(eventDetail.getProvider());
					data.setLocation(eventDetail.getLocations());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			eventHeaderDataList.add(data);
		});

		SearchQuery<EventHeaderData> result = new SearchQuery<>();
		int count = eventHeaderDao.countAll().intValue();
		result.setCount(count);
		result.setData(eventHeaderDataList);

		return result;
	}
	
	public SearchQuery<EventHeaderData> findAllEventNonLogin(String query, Integer startPage, Integer maxPage)
			throws Exception {
		List<EventHeader> dataDb = eventHeaderDao.findAllByType(EventTypeConstant.EVENT.name(), query, startPage,
				maxPage);

		List<EventHeaderData> eventHeaderDataList = new ArrayList<EventHeaderData>();

		dataDb.forEach(eventHeader -> {
			EventHeaderData data = new EventHeaderData();
			data.setId(eventHeader.getId());
			data.setEventHeaderCode(eventHeader.getEventHeaderCode());

			data.setTitle(eventHeader.getTitle());
			data.setEventTypeId(eventHeader.getEventType().getId());

			User user = userDao.getById(eventHeader.getCreatedBy());
			Profile profile = profileDao.getById(user.getProfile().getId());
			data.setFulName(profile.getFullName());
			if (eventHeader.getFile() != null) {
				data.setFileId(eventHeader.getFile().getId());
			}

			data.setIsActive(eventHeader.getIsActive());
			data.setVersion(eventHeader.getVersion());

			try {
				EventDetail eventDetail = eventDetailDao.findByHeader(eventHeader.getId());
				if (eventDetail != null) {
					data.setPrice(eventDetail.getPrice());
					data.setStartDate(eventDetail.getStartDate());
					data.setEndDate(eventDetail.getEndDate());
					data.setProvider(eventDetail.getProvider());
					data.setLocation(eventDetail.getLocations());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			eventHeaderDataList.add(data);
		});

		SearchQuery<EventHeaderData> result = new SearchQuery<>();
		int count = eventHeaderDao.countAll().intValue();
		result.setCount(count);
		result.setData(eventHeaderDataList);

		return result;
	}

	public SearchQuery<EventHeaderData> findAllCourse(String query, Integer startPage, Integer maxPage)
			throws Exception {
		List<EventHeader> dataDb = eventHeaderDao.findAllByType(EventTypeConstant.COURSE.name(), query, startPage,
				maxPage);

		List<EventHeaderData> eventHeaderDataList = new ArrayList<EventHeaderData>();

		dataDb.forEach(eventHeader -> {
			EventHeaderData data = new EventHeaderData();
			data.setId(eventHeader.getId());
			data.setEventHeaderCode(eventHeader.getEventHeaderCode());

			data.setTitle(eventHeader.getTitle());
			data.setEventTypeId(eventHeader.getEventType().getId());

			User user = userDao.getById(eventHeader.getCreatedBy());
			Profile profile = profileDao.getById(user.getProfile().getId());
			data.setFulName(profile.getFullName());
			if (eventHeader.getFile() != null) {
				data.setFileId(eventHeader.getFile().getId());
			}

			data.setIsActive(eventHeader.getIsActive());
			data.setVersion(eventHeader.getVersion());

			try {
				EventDetail eventDetail = eventDetailDao.findByHeader(eventHeader.getId());
				if (eventDetail != null) {
					data.setPrice(eventDetail.getPrice());
					data.setStartDate(eventDetail.getStartDate());
					data.setEndDate(eventDetail.getEndDate());
					data.setProvider(eventDetail.getProvider());
					data.setLocation(eventDetail.getLocations());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			eventHeaderDataList.add(data);
		});

		SearchQuery<EventHeaderData> result = new SearchQuery<>();
		int count = eventHeaderDao.countAll().intValue();
		result.setCount(count);
		result.setData(eventHeaderDataList);

		return result;
	}
	
	public SearchQuery<EventHeaderData> findAllCourseNonLogin(String query, Integer startPage, Integer maxPage)
			throws Exception {
		List<EventHeader> dataDb = eventHeaderDao.findAllByType(EventTypeConstant.COURSE.name(), query, startPage,
				maxPage);

		List<EventHeaderData> eventHeaderDataList = new ArrayList<EventHeaderData>();

		dataDb.forEach(eventHeader -> {
			EventHeaderData data = new EventHeaderData();
			data.setId(eventHeader.getId());
			data.setEventHeaderCode(eventHeader.getEventHeaderCode());

			data.setTitle(eventHeader.getTitle());
			data.setEventTypeId(eventHeader.getEventType().getId());

			User user = userDao.getById(eventHeader.getCreatedBy());
			Profile profile = profileDao.getById(user.getProfile().getId());
			data.setFulName(profile.getFullName());
			if (eventHeader.getFile() != null) {
				data.setFileId(eventHeader.getFile().getId());
			}

			data.setIsActive(eventHeader.getIsActive());
			data.setVersion(eventHeader.getVersion());

			try {
				EventDetail eventDetail = eventDetailDao.findByHeader(eventHeader.getId());
				if (eventDetail != null) {
					data.setPrice(eventDetail.getPrice());
					data.setStartDate(eventDetail.getStartDate());
					data.setEndDate(eventDetail.getEndDate());
					data.setProvider(eventDetail.getProvider());
					data.setLocation(eventDetail.getLocations());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			eventHeaderDataList.add(data);
		});

		SearchQuery<EventHeaderData> result = new SearchQuery<>();
		int count = eventHeaderDao.countAll().intValue();
		result.setCount(count);
		result.setData(eventHeaderDataList);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();
		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			EventDetail eventDetail = eventDetailDao.findByHeader(id);
			eventDetailDao.deleteById(eventDetail.getId());

			boolean isDeleted = eventHeaderDao.deleteById(id);
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

	private LocalDateTime stringToLocalDateTime(String dateTimeStr) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		return LocalDateTime.parse(dateTimeStr, formatter);
	}

}
