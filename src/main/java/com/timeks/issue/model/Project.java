package com.timeks.issue.model;

import com.timeks.base.model.AuditableBaseEntity;
import com.timeks.issue.model.dto.ProjectDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Project extends AuditableBaseEntity {
    private String name;
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Issue> issueList = new ArrayList<>();

    public static Project from(ProjectDto dto) {
        return Project.builder()
                .name(dto.getName())
                .build();
    }
}
