package com.credibanco.assessment.card.dto.response;

import com.credibanco.assessment.card.dto.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse extends BaseResponse {
    public TransactionResponse(String referenceNumber, Double buyAmount, String buyAddress, TransactionStatus status) {
        this.referenceNumber = referenceNumber;
        this.buyAmount = buyAmount;
        this.buyAddress = buyAddress;
        this.status = status;
    }

    private String referenceNumber;
    private Double buyAmount;
    private String buyAddress;
    private TransactionStatus status;
}
