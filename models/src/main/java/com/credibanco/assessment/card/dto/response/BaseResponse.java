package com.credibanco.assessment.card.dto.response;

import com.credibanco.assessment.card.dto.enums.ResponseCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private String code;
    private String message;

    public void setResponseCode(ResponseCodes responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}
