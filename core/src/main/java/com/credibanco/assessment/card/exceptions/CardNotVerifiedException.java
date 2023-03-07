package com.credibanco.assessment.card.exceptions;

import com.credibanco.assessment.card.dto.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardNotVerifiedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CardNotVerifiedException() {
    }
    public CardNotVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardNotVerifiedException(ResponseCodes response) {
        super(response.getMessage());
    }

    public CardNotVerifiedException(String message) {
        super(message);
    }

    public CardNotVerifiedException(Throwable cause) {
        super(cause);
    }

}
