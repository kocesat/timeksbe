package com.timeks.issue.controller;

import com.timeks.base.model.BaseResponse;
import com.timeks.issue.model.dto.ProjectDto;
import com.timeks.issue.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = ProjectController.BASE_URI)
@RequiredArgsConstructor
public class ProjectController {
    public static final String BASE_URI = "/api/v1/project";
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<BaseResponse> createProject(@Valid @RequestBody ProjectDto projectDto) {
        final var project = projectService.save(projectDto);
        return ResponseEntity.ok(BaseResponse.of("New Project created", ProjectDto.from(project)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllProjects() {
        final var projectDtos =
                projectService.findAll()
                        .stream()
                        .map(ProjectDto::from)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(BaseResponse.of(projectDtos));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<BaseResponse> deleteProject(@PathVariable("projectId") Long projectId) {
        projectService.deleteById(projectId);
        return ResponseEntity.ok(BaseResponse.of("Project deleted", null));
    }

}
