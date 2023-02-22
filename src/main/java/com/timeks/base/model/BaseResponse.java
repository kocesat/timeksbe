package com.timeks.base.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private String message;
    private Object data;

    public static BaseResponse of(Object data) {
        return BaseResponse.builder()
                .data(data)
                .build();
    }

    public static BaseResponse of(String message, Object data) {
        return BaseResponse.builder()
                .message(message)
                .data(data)
                .build();
    }
}
