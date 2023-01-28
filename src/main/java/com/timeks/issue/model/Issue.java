package com.timeks.issue.model;

import com.timeks.base.enums.Status;
import com.timeks.base.enums.converters.StatusConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tissue")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Convert(converter = StatusConverter.class)
    private Status status;

    public static Issue of(IssueDto dto) {
        return Issue.builder()
                .description(dto.getDescription())
                .status(Status.getEnum(dto.getStatusCode()))
                .build();
    }
}
