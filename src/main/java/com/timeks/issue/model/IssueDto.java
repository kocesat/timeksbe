package com.timeks.issue.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.timeks.base.enums.IssueStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueDto {
    private String key;
    private String description;
    @Builder.Default
    @NotNull(message = "Status code cannot be null")
    private Integer statusCode = IssueStatus.TODO.getCode();
    private String statusText;
    @NotNull(message = "Issue type code cannot be null")
    private Integer issueTypeCode;
    private String issueTypeText;

    public static IssueDto from(Issue issue) {
        return IssueDto.builder()
                .key(issue.getKey().toString())
                .description(issue.getDescription())
                .statusCode(issue.getIssueStatus().getCode())
                .statusText(issue.getIssueStatus().getText())
                .issueTypeCode(issue.getIssueType().getCode())
                .issueTypeText(issue.getIssueType().getText())
                .build();
    }
}
