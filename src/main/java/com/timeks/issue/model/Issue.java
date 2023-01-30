package com.timeks.issue.model;

import com.timeks.base.enums.IssueStatus;
import com.timeks.base.enums.IssueType;
import com.timeks.base.enums.converters.IssueStatusConverter;
import com.timeks.base.enums.converters.IssueTypeConverter;
import com.timeks.base.model.AuditableBaseEntity;
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

    public static Issue from(IssueDto dto) {
        return Issue.builder()
                .description(dto.getDescription())
                .issueStatus(IssueStatus.getEnum(dto.getStatusCode()))
                .issueType(IssueType.getEnum(dto.getIssueTypeCode()))
                .closed(dto.isClosed())
                .build();
    }
}
