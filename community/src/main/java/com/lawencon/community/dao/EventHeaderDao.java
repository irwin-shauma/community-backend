package com.lawencon.community.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.EventHeader;
import com.lawencon.community.model.EventType;

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
}
