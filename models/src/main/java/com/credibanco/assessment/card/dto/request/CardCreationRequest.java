package com.credibanco.assessment.card.dto.request;

import com.credibanco.assessment.card.dto.enums.CardType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CardCreationRequest {
    @Valid
    @NotEmpty(message = "Numero de tarjeta no puede ser vacio")
    @Size(message = "Longitud maxima del numero de tarjeta debe ser 19", max = 19)
    @Size(message = "Longitud minima del numero de tarjeta debe ser 16", min = 16)
    private String pan;
    private String customerName;
    private String customerId;
    private String customerPhone;
    private CardType type;

}
