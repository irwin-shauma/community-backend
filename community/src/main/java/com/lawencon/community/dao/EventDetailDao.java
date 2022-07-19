package com.lawencon.community.dao;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.EventDetail;
import com.lawencon.community.model.File;

@Repository
public class EventDetailDao extends AbstractJpaDao<EventDetail> {
	
	public EventDetail findByHeader(String id) throws Exception {
		String sql = "SELECT * FROM event_header WHERE event_header_id = :id";
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
				
				File file = new File();
				file.setId(objArr[2].toString());
				
				eventDetail.setFile(file);
				eventDetail.setPrice(Float.valueOf(objArr[3].toString()));
				eventDetail.setDates(((Date) objArr[4]).toLocalDate());
				eventDetail.setStarts(((Time) objArr[5]).toLocalTime());
				eventDetail.setEnds(((Time) objArr[6]).toLocalTime());
				eventDetail.setProvider(objArr[7].toString());
				eventDetail.setLocations(objArr[8].toString());
				eventDetail.setCreatedBy(objArr[9].toString());
				if (objArr[10] != null) {
					eventDetail.setCreatedAt(((Timestamp) objArr[10]).toLocalDateTime());
				}
				eventDetail.setUpdatedBy(objArr[11].toString());
				if (objArr[12] != null) {
					eventDetail.setUpdatedAt(((Timestamp) objArr[12]).toLocalDateTime());
				}
				eventDetail.setIsActive(Boolean.valueOf(objArr[13].toString()));
				eventDetail.setVersion(Integer.valueOf(objArr[14].toString()));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
