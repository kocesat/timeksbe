package com.timeks.issue.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.timeks.issue.model.Project;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto {
    private Long key;
    @NotBlank(message = "Name cannot be null")
    private String name;

    public static ProjectDto from(Project project) {
        return ProjectDto.builder()
                .key(project.getId())
                .name(project.getName())
                .build();
    }
}
