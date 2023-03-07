package com.credibanco.assessment.card.exceptions;

import com.credibanco.assessment.card.dto.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidVerificationCodeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidVerificationCodeException() {
    }
    public InvalidVerificationCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidVerificationCodeException(ResponseCodes response) {
        super(response.getMessage());
    }

    public InvalidVerificationCodeException(String message) {
        super(message);
    }

    public InvalidVerificationCodeException(Throwable cause) {
        super(cause);
    }

}
