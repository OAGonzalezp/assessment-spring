package com.credibanco.assessment.card.dto.response;

import com.credibanco.assessment.card.dto.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionCreationResponse extends BaseResponse {
    private String referenceNumber;
    private TransactionStatus status;
}
