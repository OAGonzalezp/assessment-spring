package com.credibanco.assessment.card.advice;

import com.credibanco.assessment.card.dto.enums.ResponseCodes;
import com.credibanco.assessment.card.dto.response.DataResponse;
import com.credibanco.assessment.card.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdvice {

    @Autowired
    HttpServletRequest httpServletRequest;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processUnmergeException(final MethodArgumentNotValidException ex) {

        List<String> messages = ex.getBindingResult().getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        DataResponse response = new DataResponse();
        response.setCode(ResponseCodes.ERROR.getCode());
        response.setMessage(messages.stream().collect(Collectors.joining(",")));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardNotFoundException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processCardNotFoundException(final Exception ex) {
        return new ResponseEntity(ResponseCodes.CARD_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidVerificationCodeException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processInvalidVerificationCodeException(final Exception ex) {
        return new ResponseEntity(ResponseCodes.INVALID_VERIFICATION_NUMBER, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardNotVerifiedException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processCardNotVerifiedException(final Exception ex) {
        return new ResponseEntity(ResponseCodes.CARD_NOT_VERIFIED, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotCanceledException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processTransactionNotCanceledException(final Exception ex) {
        return new ResponseEntity(ResponseCodes.PURCHASE_NOT_CANCELED, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processTransactionNotFoundException(final Exception ex) {
        return new ResponseEntity(ResponseCodes.INVALID_REFERENCE_NUMBER, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processGenericException(final Exception ex) {

        return new ResponseEntity(ResponseCodes.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
