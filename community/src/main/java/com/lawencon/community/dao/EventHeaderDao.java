package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.EventHeader;
import com.lawencon.community.model.EventType;
import com.lawencon.community.model.File;
import com.lawencon.community.model.User;

@Repository
public class EventHeaderDao extends AbstractJpaDao<EventHeader> {
	
	public EventHeader findByTitle(String title) throws Exception{
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT eh.id, eh.eventHeaderCode, eh.title, eh.eventType FROM event_header eh ")
					.append(" INNER JOIN event_type et ON et.id = eh.eventType ")
					.append(" WHERE eh.title = : title ");
		
		EventHeader eventHeader = null;
		try {
			Object result = createNativeQuery(sqlBuilder.toString())
					.setParameter("title", title)
					.getSingleResult();
			if(result != null) {
				Object[] objArr = (Object[]) result;
				eventHeader = new EventHeader();
				eventHeader.setId(objArr[0].toString());
				eventHeader.setEventHeaderCode(objArr[1].toString());
				eventHeader.setTitle(objArr[2].toString());
				
				EventType eventType = new EventType();
				eventType.setId(objArr[3].toString());
				
				eventHeader.setEventType(eventType);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return eventHeader;
	}
	
	public List<EventHeader> findAllByUser(String id, String query, Integer startPage, Integer maxPage) throws Exception {
		String sql = "SELECT * FROM event_header WHERE user_id= :id ORDER BY created_at DESC ";
		
		List<?> result = createNativeQuery(sql).setParameter("id", id).getResultList();
		
		List<EventHeader> eventHeaders = new ArrayList<>();
		
		result.forEach(data -> {
			Object[] objArr = (Object[]) data;
			EventHeader eventHeader = new EventHeader();
			eventHeader.setId(objArr[0].toString());
			eventHeader.setEventHeaderCode(objArr[1].toString());
			
			EventType eventTypes = new EventType();
			eventTypes.setId(objArr[2].toString());
			eventHeader.setEventType(eventTypes);
			
			if(objArr[3] != null) {
				File file = new File();
				file.setId(objArr[3].toString());
				eventHeader.setFile(file);
			}
			
			eventHeader.setTitle(objArr[4].toString());
			
			User user = new User();
			user.setId(objArr[5].toString());
			eventHeader.setUser(user);
			
			eventHeader.setCreatedAt(((Timestamp)objArr[6]).toLocalDateTime());
			eventHeader.setCreatedBy(objArr[7].toString());
			
			if(objArr[8] != null) {
				eventHeader.setUpdatedAt(((Timestamp)objArr[8]).toLocalDateTime());
			}
			
			if(objArr[9] != null) {
				eventHeader.setUpdatedBy(objArr[9].toString());
			}
			
			eventHeader.setIsActive(Boolean.valueOf(objArr[10].toString()));
			eventHeader.setVersion(Integer.valueOf(objArr[11].toString()));

			eventHeaders.add(eventHeader);
		});
		return eventHeaders;
	}
	
	public List<EventHeader> findAllByType(String eventType, String query, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append("SELECT eh.* FROM event_header eh INNER JOIN event_type et ON eh.event_type_id = et.id")
				.append(" WHERE et.\"type\" = :event ORDER BY created_at DESC ");
		
		List<?> result = createNativeQuery(sql.toString()).setParameter("event", eventType).getResultList();
		
		List<EventHeader> eventHeaders = new ArrayList<>();
		
		result.forEach(data -> {
			Object[] objArr = (Object[]) data;
			EventHeader eventHeader = new EventHeader();
			eventHeader.setId(objArr[0].toString());
			eventHeader.setEventHeaderCode(objArr[1].toString());
			
			EventType eventTypes = new EventType();
			eventTypes.setId(objArr[2].toString());
			eventHeader.setEventType(eventTypes);
			
			if(objArr[3] != null) {
				File file = new File();
				file.setId(objArr[3].toString());
				eventHeader.setFile(file);
			}
			
			eventHeader.setTitle(objArr[4].toString());
			
			User user = new User();
			user.setId(objArr[5].toString());
			eventHeader.setUser(user);
			
			eventHeader.setCreatedAt(((Timestamp)objArr[6]).toLocalDateTime());
			eventHeader.setCreatedBy(objArr[7].toString());
			
			if(objArr[8] != null) {
				eventHeader.setUpdatedAt(((Timestamp)objArr[8]).toLocalDateTime());
			}
			
			if(objArr[9] != null) {
				eventHeader.setUpdatedBy(objArr[9].toString());
			}
			
			eventHeader.setIsActive(Boolean.valueOf(objArr[10].toString()));
			eventHeader.setVersion(Integer.valueOf(objArr[11].toString()));
			
			eventHeaders.add(eventHeader);
		});
		
		return eventHeaders;
	}
	
	public Long countAllEvent(String type) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT COUNT(eh.id) FROM event_header eh ")
		.append(" INNER JOIN event_type et ON et.id = eh.event_type_id ")
		.append(" WHERE et.type = :type ");
		Long total = 0L;
		try {
			Object result = createNativeQuery(sqlBuilder.toString()).setParameter("type", type).getSingleResult();
			if( result != null) {
				 total = Long.valueOf(result.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
	

}
