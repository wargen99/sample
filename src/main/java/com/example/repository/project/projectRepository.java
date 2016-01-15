package com.example.repository.project;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.dto.projectInfo.Project;

import java.lang.String;
import java.util.List;

public interface projectRepository extends PagingAndSortingRepository<Project, Integer>{

	List<Project> findByProjectName(String projectname);
}
