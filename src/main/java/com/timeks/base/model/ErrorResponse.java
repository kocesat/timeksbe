package com.timeks.base.model;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ErrorResponse {
    private String error;
    @Builder.Default
    private List<String> fieldErrors = new ArrayList<>();
}
