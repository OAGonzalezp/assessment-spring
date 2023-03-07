package com.credibanco.assessment.card.exceptions;

import com.credibanco.assessment.card.dto.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TransactionNotCanceledException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TransactionNotCanceledException() {
    }
    public TransactionNotCanceledException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionNotCanceledException(ResponseCodes response) {
        super(response.getMessage());
    }

    public TransactionNotCanceledException(String message) {
        super(message);
    }

    public TransactionNotCanceledException(Throwable cause) {
        super(cause);
    }

}
