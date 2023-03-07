package com.credibanco.assessment.card.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardValidationRequest {
    private Integer validationCode;
    private String pan;
}
