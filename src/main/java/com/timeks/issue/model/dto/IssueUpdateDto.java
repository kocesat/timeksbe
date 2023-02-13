package com.timeks.issue.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueUpdateDto {
    @NotNull(message = "Issue key cannot be null")
    private Long key;
    private String description;
    private Integer statusCode;
    private Integer issueTypeCode;
    private Boolean closed;
}
