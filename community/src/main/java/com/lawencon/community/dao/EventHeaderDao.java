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
		String sql = "SELECT * FROM event_header WHERE user_id= :id";
		
		List<?> result = createNativeQuery(sql).setParameter("id", id).getResultList();
		
		List<EventHeader> eventHeaders = new ArrayList<>();
		
		result.forEach(data -> {
			Object[] objArr = (Object[]) data;
			EventHeader eventHeader = new EventHeader();
			eventHeader.setId(objArr[0].toString());
			eventHeader.setEventHeaderCode(objArr[1].toString());
			
			EventType eventType = new EventType();
			eventType.setId(objArr[2].toString());
			eventHeader.setEventType(eventType);

			eventHeader.setTitle(objArr[3].toString());

			File file = new File();
			file.setId(objArr[10].toString());

			User user = new User();
			user.setId(objArr[11].toString());

			eventHeader.setCreatedAt(((Timestamp)objArr[4]).toLocalDateTime());
			eventHeader.setCreatedBy(objArr[5].toString());

			if(objArr[6] != null) {
				eventHeader.setUpdatedAt(((Timestamp)objArr[6]).toLocalDateTime());
			}

			if(objArr[7] != null) {
				eventHeader.setUpdatedBy(objArr[7].toString());
			}

			eventHeader.setIsActive(Boolean.valueOf(objArr[8].toString()));
			eventHeader.setVersion(Integer.valueOf(objArr[9].toString()));

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
