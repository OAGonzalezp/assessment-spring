package com.credibanco.assessment.card.service.impl;

import com.credibanco.assessment.card.dao.ICardDao;
import com.credibanco.assessment.card.dto.request.CardCreationRequest;
import com.credibanco.assessment.card.dto.request.CardDeleteRequest;
import com.credibanco.assessment.card.dto.request.CardValidationRequest;
import com.credibanco.assessment.card.dto.response.BaseResponse;
import com.credibanco.assessment.card.dto.response.CardCreationResponse;
import com.credibanco.assessment.card.dto.response.CardResponse;
import com.credibanco.assessment.card.dto.response.CardValidationResponse;
import com.credibanco.assessment.card.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements ICardService {

    @Autowired
    ICardDao iCardDao;

    @Override
    public CardCreationResponse save(CardCreationRequest request) {
        return iCardDao.save(request);
    }

    @Override
    public CardValidationResponse validate(CardValidationRequest request) {
        return iCardDao.validate(request);
    }

    @Override
    public CardResponse consult(String pan) {
        return iCardDao.consult(pan);
    }

    @Override
    public List<CardResponse> findAll() {
        return iCardDao.findAll();
    }

    @Override
    public BaseResponse deleteCard(CardDeleteRequest request) {
        return iCardDao.deleteCard(request);
    }
}
