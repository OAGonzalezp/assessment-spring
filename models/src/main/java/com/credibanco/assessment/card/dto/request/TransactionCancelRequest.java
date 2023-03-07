package com.credibanco.assessment.card.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TransactionCancelRequest {
    private String pan;
    private String referenceNumber;
    private Double buyAmount;
}
