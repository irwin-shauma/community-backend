package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.PremiumTypeDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.premiumtype.PremiumTypeData;
import com.lawencon.community.dto.premiumtype.PremiumTypeFindByIdRes;
import com.lawencon.community.dto.premiumtype.PremiumTypeInsertReq;
import com.lawencon.community.dto.premiumtype.PremiumTypeUpdateReq;
import com.lawencon.community.model.PremiumType;
import com.lawencon.model.SearchQuery;

@Service
public class PremiumTypeService extends BaseCoreService<PremiumType> {

	@Autowired
	private PremiumTypeDao premiumTypeDao;

	public InsertRes insert(PremiumTypeInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {
			PremiumType premiumType = new PremiumType();
			premiumType.setPremiumTypeCode(code);
			premiumType.setPrice(data.getPrice());
			premiumType.setDuration(data.getDuration());
			premiumType.setIsActive(true);

			begin();
			PremiumType premiumTypeInsert = save(premiumType);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(premiumTypeInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public UpdateRes update(PremiumTypeUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			begin();
			PremiumType premiumTypeDb = premiumTypeDao.getByIdWithoutDetach(data.getId());

			premiumTypeDb.setPrice(data.getPrice());
			premiumTypeDb.setDuration(data.getDuration());
			premiumTypeDb.setIsActive(data.getIsActive());

			PremiumType premiumTypeUpdate = premiumTypeDao.save(premiumTypeDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(premiumTypeUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	public PremiumTypeFindByIdRes getById(String id) throws Exception {
		PremiumType premiumTypeDb = premiumTypeDao.getById(id);

		PremiumTypeData data = new PremiumTypeData();
		data.setId(premiumTypeDb.getId());
		data.setPremiumTypeCode(premiumTypeDb.getPremiumTypeCode());
		data.setPrice(premiumTypeDb.getPrice());
		data.setDuration(premiumTypeDb.getDuration());
		data.setIsActive(premiumTypeDb.getIsActive());
		data.setVersion(premiumTypeDb.getVersion());

		PremiumTypeFindByIdRes result = new PremiumTypeFindByIdRes();
		result.setData(data);

		return result;

	}

	public SearchQuery<PremiumTypeData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PremiumType> dataDb = premiumTypeDao.findAll(query, startPage, maxPage);

		List<PremiumTypeData> premiumTypeDataList = new ArrayList<PremiumTypeData>();

		dataDb.getData().forEach(premiumType -> {
			PremiumTypeData data = new PremiumTypeData();
			data.setId(premiumType.getId());
			data.setPremiumTypeCode(premiumType.getPremiumTypeCode());
			data.setPrice(premiumType.getPrice());
			data.setDuration(premiumType.getDuration());
			data.setIsActive(premiumType.getIsActive());
			data.setVersion(premiumType.getVersion());

			premiumTypeDataList.add(data);
		});

		SearchQuery<PremiumTypeData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(premiumTypeDataList);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = premiumTypeDao.deleteById(id);
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
