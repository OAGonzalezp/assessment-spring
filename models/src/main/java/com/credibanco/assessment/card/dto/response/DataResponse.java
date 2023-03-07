package com.credibanco.assessment.card.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> {

    private String code;
    private String message;
    private T data;

    public DataResponse() {
    }

    public DataResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public DataResponse(final T data) {
        this.data = data;
    }

}
