package com.lawencon.community.dao;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.EventDetail;
import com.lawencon.community.model.EventHeader;

@Repository
public class EventDetailDao extends AbstractJpaDao<EventDetail> {
	
	public EventDetail findByHeader(String id) throws Exception {
		String sql = "SELECT * FROM event_detail WHERE event_header_id = :id";
		EventDetail eventDetail = null;
		
		try {
			Object result = createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			if(result != null) {
				Object[] objArr = (Object[]) result;
				eventDetail = new EventDetail();
				eventDetail.setId(objArr[0].toString());
				eventDetail.setEventDetailCode(objArr[1].toString());
				
				EventHeader eventHeader = new EventHeader();
				eventHeader.setId(objArr[2].toString());
				eventDetail.setEventHeader(eventHeader);
				
				eventDetail.setPrice(Float.valueOf(objArr[3].toString()));
				eventDetail.setStartDate( ((Timestamp) objArr[4]).toLocalDateTime());
				eventDetail.setEndDate( ((Timestamp) objArr[5]).toLocalDateTime());
				eventDetail.setProvider(objArr[6].toString());
				eventDetail.setLocations(objArr[7].toString());
				if (objArr[8] != null) {
					eventDetail.setCreatedAt(((Timestamp) objArr[8]).toLocalDateTime());
				}
				eventDetail.setCreatedBy(objArr[9].toString());
				eventDetail.setUpdatedBy(objArr[10].toString());
				if (objArr[11] != null) {
					eventDetail.setUpdatedAt(((Timestamp) objArr[11]).toLocalDateTime());
				}
				eventDetail.setIsActive(Boolean.valueOf(objArr[12].toString()));
				eventDetail.setVersion(Integer.valueOf(objArr[13].toString()));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
