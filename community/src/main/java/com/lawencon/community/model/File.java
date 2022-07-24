package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "file " ,uniqueConstraints = {
		@UniqueConstraint(
				name = "file_bk",
				columnNames = "file_code"
			)
})
public class File extends BaseEntity{
	
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "file_code")
	private String fileCode;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_ext")
	private String fileExtension;

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	

}
