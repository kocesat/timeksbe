package com.timeks.issue.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.timeks.base.enums.IssueStatus;
import com.timeks.issue.model.Issue;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueDto {
    private Long key;
    private String description;
    @Builder.Default
    @NotNull(message = "Status code cannot be null")
    private Integer statusCode = IssueStatus.TODO.getCode();
    private String statusText;
    @NotNull(message = "Issue type code cannot be null")
    private Integer issueTypeCode;
    private String issueTypeText;
    private boolean closed;
    private Long projectId;
    private ProjectDto project;

    public static IssueDto from(Issue issue) {
        return IssueDto.builder()
                .key(issue.getId())
                .description(issue.getDescription())
                .statusCode(issue.getIssueStatus().getCode())
                .statusText(issue.getIssueStatus().getText())
                .issueTypeCode(issue.getIssueType().getCode())
                .issueTypeText(issue.getIssueType().getText())
                .closed(issue.isClosed())
                .project(Objects.isNull(issue.getProject()) ? null : ProjectDto.from(issue.getProject()))
                .build();
    }
}
