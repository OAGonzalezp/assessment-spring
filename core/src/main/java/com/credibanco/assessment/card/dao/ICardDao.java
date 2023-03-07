package com.credibanco.assessment.card.dao;

import com.credibanco.assessment.card.dto.request.CardCreationRequest;
import com.credibanco.assessment.card.dto.request.CardDeleteRequest;
import com.credibanco.assessment.card.dto.request.CardValidationRequest;
import com.credibanco.assessment.card.dto.response.BaseResponse;
import com.credibanco.assessment.card.dto.response.CardCreationResponse;
import com.credibanco.assessment.card.dto.response.CardResponse;
import com.credibanco.assessment.card.dto.response.CardValidationResponse;

import java.util.List;

public interface ICardDao {

    CardCreationResponse save(CardCreationRequest request);
    CardValidationResponse validate(CardValidationRequest request);
    CardResponse consult(String pan);

    List<CardResponse> findAll();
    BaseResponse deleteCard(CardDeleteRequest request);
}
