package com.timeks.base.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BaseResponse {
    private String message;
    private Object data;

    public static BaseResponse of(Object data) {
        return BaseResponse.builder()
                .data(data)
                .build();
    }

    public static BaseResponse withMessage(String message, Object data) {
        return BaseResponse.builder()
                .message(message)
                .data(data)
                .build();
    }
}
