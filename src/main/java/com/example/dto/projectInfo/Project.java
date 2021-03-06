package com.example.dto.projectInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="project_info")
public class Project {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int idx;
	private String projectName;
	//org.hibernate.tool.hbm2ddl.SchemaExport  : Column length too big for column 'projectDescription' (max = 21845); use BLOB or TEXT instead
	@Column(name ="projectDescription", columnDefinition="TEXT(65535)")
	private String projectDescription;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

}
