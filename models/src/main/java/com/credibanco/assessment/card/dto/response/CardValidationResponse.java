package com.credibanco.assessment.card.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardValidationResponse extends BaseResponse {
    private String pan;
}
