package com.credibanco.assessment.card.exceptions;

import com.credibanco.assessment.card.dto.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CardInvalidStatusException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CardInvalidStatusException() {
    }
    public CardInvalidStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardInvalidStatusException(ResponseCodes response) {
        super(response.getMessage());
    }

    public CardInvalidStatusException(String message) {
        super(message);
    }

    public CardInvalidStatusException(Throwable cause) {
        super(cause);
    }

}
