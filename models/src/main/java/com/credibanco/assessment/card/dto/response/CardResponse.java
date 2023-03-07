package com.credibanco.assessment.card.dto.response;

import com.credibanco.assessment.card.dto.enums.CardType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardResponse extends BaseResponse {
    private String pan;
    private String maskPan;
    private String customerName;
    private String customerId;
    private String customerPhone;
    private CardType type;
}
