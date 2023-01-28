package com.timeks.issue.model;

import com.timeks.base.enums.Status;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class IssueDto {
    private Long key;
    private String description;
    @Builder.Default
    @NotNull(message = "Status code cannot be null")
    private Integer statusCode = Status.TODO.getCode();
    private String statusText;

    public static IssueDto of(Issue issue) {
        return IssueDto.builder()
                .key(issue.getId())
                .description(issue.getDescription())
                .statusCode(issue.getStatus().getCode())
                .statusText(issue.getStatus().getText())
                .build();
    }
}
