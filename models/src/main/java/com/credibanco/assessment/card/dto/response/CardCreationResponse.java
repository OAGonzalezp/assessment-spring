package com.credibanco.assessment.card.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardCreationResponse extends BaseResponse {
    private Integer validationCode;
    private String pan;
    private String hashIdentifier;
}
