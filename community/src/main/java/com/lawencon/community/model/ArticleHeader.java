package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "article_header", uniqueConstraints = { @UniqueConstraint(
		name = "article_header_bk", 
		columnNames = "article_header_code") 
})
public class ArticleHeader extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name="article_header_code")
	private String articleHeaderCode;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	private String title;
	private String contents;

	public String getArticleHeaderCode() {
		return articleHeaderCode;
	}

	public void setArticleHeaderCode(String articleHeaderCode) {
		this.articleHeaderCode = articleHeaderCode;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
