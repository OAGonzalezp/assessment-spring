package com.credibanco.assessment.card.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TransactionCreationRequest {
    private String pan;
    @Valid
    @NotEmpty(message = "Numero de compra no puede ser vacio")
    @Size(message = "Longitud maxima del numero de compra debe ser 6", max = 6)
    @Size(message = "Longitud minima del numero de compra debe ser 6", min = 6)
    private String referenceNumber;
    private Double buyAmount;
    private String buyAddress;
}
