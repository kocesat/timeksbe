package com.timeks.issue.model;

import com.timeks.base.enums.IssueStatus;
import com.timeks.base.enums.IssueType;
import com.timeks.base.enums.converters.IssueStatusConverter;
import com.timeks.base.enums.converters.IssueTypeConverter;
import com.timeks.base.model.AuditableBaseEntity;
import com.timeks.issue.model.dto.IssueDto;
import com.timeks.issue.model.dto.IssueUpdateDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "issues")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Issue extends AuditableBaseEntity {
    private String description;
    @Convert(converter = IssueStatusConverter.class)
    @Column(name = "status_code")
    private IssueStatus issueStatus;
    @Convert(converter = IssueTypeConverter.class)
    @Column(name = "type_code")
    private IssueType issueType;
    private boolean closed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Issue update(IssueUpdateDto dto) {
        if (dto.getDescription() != null) {
            description = dto.getDescription();
        }
        if (dto.getStatusCode() != null) {
            issueStatus = IssueStatus.getEnum(dto.getStatusCode());
        }
        if (dto.getIssueTypeCode() != null) {
            issueType = IssueType.getEnum(dto.getIssueTypeCode());
        }
        if (dto.getClosed() != null) {
            closed = dto.getClosed();
        }
        return this;
    }

    public static Issue from(IssueDto dto) {
        return Issue.builder()
                .description(dto.getDescription())
                .issueStatus(IssueStatus.getEnum(dto.getStatusCode()))
                .issueType(IssueType.getEnum(dto.getIssueTypeCode()))
                .closed(dto.isClosed())
                .build();
    }
}
