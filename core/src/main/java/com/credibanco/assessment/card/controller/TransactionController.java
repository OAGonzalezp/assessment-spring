package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.dto.request.TransactionCancelRequest;
import com.credibanco.assessment.card.dto.request.TransactionCreationRequest;
import com.credibanco.assessment.card.dto.response.BaseResponse;
import com.credibanco.assessment.card.dto.response.TransactionCancelResponse;
import com.credibanco.assessment.card.dto.response.TransactionCreationResponse;
import com.credibanco.assessment.card.dto.response.TransactionsResponse;
import com.credibanco.assessment.card.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class TransactionController {
    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    ITransactionService transactionService;

    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionCreationResponse> createCard(@RequestBody final TransactionCreationRequest request){

        TransactionCreationResponse response = new TransactionCreationResponse();
        try {
            response = transactionService.save(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception exception) {
            response.setCode("01");
            response.setMessage("Fallido");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/transaction/{pan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionsResponse> getTransaction(@PathVariable String pan){

        BaseResponse response = new BaseResponse();
        try {
            return new ResponseEntity<>(transactionService.findAll(pan), HttpStatus.OK);
        } catch (Exception exception) {
            response.setCode("01");
            response.setMessage("Fallido");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionCancelResponse> deleteTransaction(@RequestBody final TransactionCancelRequest request){

        TransactionCancelResponse response = new TransactionCancelResponse();
        try {
            response = transactionService.cancelTransaction(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            response.setCode("01");
            response.setMessage("Fallido");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
