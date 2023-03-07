package com.credibanco.assessment.card.exceptions;

import com.credibanco.assessment.card.dto.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CardNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CardNotFoundException() {
    }
    public CardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardNotFoundException(ResponseCodes response) {
        super(response.getMessage());
    }

    public CardNotFoundException(String message) {
        super(message);
    }

    public CardNotFoundException(Throwable cause) {
        super(cause);
    }

}
