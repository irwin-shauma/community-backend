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
	
	public List<ArticleHeader> findByTitle(String title) throws Exception {
		String sql = "SELECT * FROM article_header WHERE title = :title";
		List<?> result = createNativeQuery(sql)
						.setParameter("title", title)
						.getResultList();
		List<ArticleHeader> articles = new ArrayList<ArticleHeader>();
		
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			ArticleHeader article = new ArticleHeader();
			article.setId(objArr[0].toString());
			article.setArticleHeaderCode(objArr[1].toString());
			File file = new File();
			file.setId(objArr[2].toString());
			article.setFileId(file);
			article.setTitle(objArr[3].toString());
			article.setContents(objArr[4].toString());
			article.setCreatedBy(objArr[5].toString());
			if (objArr[6] != null) {
				article.setCreatedAt(((Timestamp) objArr[6]).toLocalDateTime());
			}
			article.setUpdatedBy(objArr[7].toString());
			if (objArr[8] != null) {
				article.setUpdatedAt(((Timestamp) objArr[8]).toLocalDateTime());
			}
			article.setIsActive(Boolean.valueOf(objArr[9].toString()));
			article.setVersion(Integer.valueOf(objArr[10].toString()));
			
			articles.add(article);
		});
		return articles;
	}

}
