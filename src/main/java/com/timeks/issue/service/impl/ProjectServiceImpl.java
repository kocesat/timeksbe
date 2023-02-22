package com.timeks.issue.service.impl;

import com.timeks.issue.exception.ProjectNotFound;
import com.timeks.issue.model.Project;
import com.timeks.issue.model.dto.ProjectDto;
import com.timeks.issue.repository.ProjectRepository;
import com.timeks.issue.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project save(ProjectDto projectDto) {
        final Project project = Project.from(projectDto);
        return projectRepository.save(project);
    }

    @Override
    public Project findById(Long id) {
        final var project =
                projectRepository.findById(id).orElseThrow(ProjectNotFound::new);
        return project;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        final var project = findById(id);
        projectRepository.deleteById(id);
    }
}
