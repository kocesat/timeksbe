package com.timeks.issue.service;

import com.timeks.issue.model.Project;
import com.timeks.issue.model.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    Project save(ProjectDto dto);

    Project findById(Long id);

    List<Project> findAll();

    void deleteById(Long id);
}
