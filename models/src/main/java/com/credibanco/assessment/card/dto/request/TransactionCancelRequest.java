package com.credibanco.assessment.card.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionCancelRequest {
    private String hashIdentifier;
    private String referenceNumber;
    private Double buyAmount;
}
