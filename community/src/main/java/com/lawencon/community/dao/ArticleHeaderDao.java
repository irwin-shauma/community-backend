package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.ArticleHeader;
import com.lawencon.community.model.File;

@Repository
public class ArticleHeaderDao extends AbstractJpaDao<ArticleHeader> {

	public List<ArticleHeader> findAllByTitle(String title) throws Exception {
		String sql = "SELECT * FROM article_header WHERE title = :title";

		List<?> result = createNativeQuery(sql)
				.setParameter("title", title)
				.getResultList();

		List<ArticleHeader> articleHeaders = new ArrayList<>();

		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			ArticleHeader articleHeader = new ArticleHeader();
			articleHeader.setId(objArr[0].toString());
			
			articleHeader.setArticleHeaderCode(objArr[1].toString());
			
			File file = new File();
			file.setId(objArr[2].toString());
			
			articleHeader.setFile(file);
			articleHeader.setTitle(objArr[3].toString());
			articleHeader.setContents(objArr[4].toString());
			articleHeader.setCreatedBy(objArr[5].toString());
			if (objArr[6] != null) {
				articleHeader.setCreatedAt(((Timestamp) objArr[6]).toLocalDateTime());
			}
			articleHeader.setUpdatedBy(objArr[7].toString());
			if (objArr[8] != null) {
				articleHeader.setUpdatedAt(((Timestamp) objArr[8]).toLocalDateTime());
			}
			articleHeader.setIsActive(Boolean.valueOf(objArr[9].toString()));
			articleHeader.setVersion(Integer.valueOf(objArr[10].toString()));

			articleHeaders.add(articleHeader);

		});
		return articleHeaders;
	}
	
}
