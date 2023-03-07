package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.dto.request.CardCreationRequest;
import com.credibanco.assessment.card.dto.request.CardDeleteRequest;
import com.credibanco.assessment.card.dto.request.CardValidationRequest;
import com.credibanco.assessment.card.dto.response.*;
import com.credibanco.assessment.card.service.ICardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class CardController {
    Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    ICardService iCardService;

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse> test(){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/card", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardCreationResponse> createCard(@RequestBody @Valid final CardCreationRequest request){

        CardCreationResponse response = iCardService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/card", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardValidationResponse> updateCard(@RequestBody final CardValidationRequest request){

        CardValidationResponse response = iCardService.validate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/card/{pan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardResponse> getCard(@PathVariable String pan){

        CardResponse response = iCardService.consult(pan);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardResponse>> getCards(){

        List<CardResponse> response = iCardService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/card", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> deleteCard(@RequestBody final CardDeleteRequest request){

        BaseResponse response = iCardService.deleteCard(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
