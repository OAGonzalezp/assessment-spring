package com.credibanco.assessment.card.dto.enums;

import com.credibanco.assessment.card.dto.response.DataResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCodes {
    OK("1", null),
    SUCCESS("00", "Exito"),
    FAILURE("01", "Fallido"),
    CARD_NOT_FOUND("01", "Tarjeta no existe"),
    INVALID_VERIFICATION_NUMBER("02", "Número de validación inválido"),
    CARD_DELETED("00", "Se ha eliminado la tarjeta"),
    CARD_NOT_DELETED("01", "No se ha eliminado la tarjeta"),
    PURCHASE_SUCCESS("00", "Compra exitosa"),
    CARD_NOT_VERIFIED("02", "Tarjeta no enrolada"),
    PURCHASE_CANCELED("00", "Compra anulada"),
    INVALID_REFERENCE_NUMBER("01", "Numero de referencia inválido"),
    PURCHASE_NOT_CANCELED("02", "No se puede anular transacción"),
    ERROR("01", "Ha ocurrido un error inesperado");

    private DataResponse data = null;

    private String code;
    private String message;

    ResponseCodes(String code, String msg) {
        data = new DataResponse(code, msg);
        this.code = code;
        this.message = msg;
    }

    @JsonValue
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public final DataResponse value() {
        return data;
    }

}
