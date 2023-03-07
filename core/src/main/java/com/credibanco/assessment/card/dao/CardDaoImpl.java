package com.credibanco.assessment.card.dao;

import com.credibanco.assessment.card.dto.enums.ResponseCodes;
import com.credibanco.assessment.card.exceptions.CardNotFoundException;
import com.credibanco.assessment.card.exceptions.InvalidVerificationCodeException;
import com.credibanco.assessment.card.model.CardEntity;
import com.credibanco.assessment.card.dto.enums.CardStatus;
import com.credibanco.assessment.card.dto.request.CardCreationRequest;
import com.credibanco.assessment.card.dto.request.CardDeleteRequest;
import com.credibanco.assessment.card.dto.request.CardValidationRequest;
import com.credibanco.assessment.card.dto.response.BaseResponse;
import com.credibanco.assessment.card.dto.response.CardCreationResponse;
import com.credibanco.assessment.card.dto.response.CardResponse;
import com.credibanco.assessment.card.dto.response.CardValidationResponse;
import com.credibanco.assessment.card.repository.ICardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CardDaoImpl implements ICardDao{

    @Autowired
    ICardRepository cardRepository;

    @Override
    public CardCreationResponse save(CardCreationRequest request) {
        CardCreationResponse response = new CardCreationResponse();
        CardEntity entity = new CardEntity();
        entity.setCustomerId(request.getCustomerId());
        entity.setCustomerName(request.getCustomerName());
        entity.setCustomerPhone(request.getCustomerPhone());
        entity.setPan(request.getPan());
        entity.setType(request.getType());
        entity.setStatus(CardStatus.CREADA);

        entity.setValidationCode(getCode());
        entity.setHashIdentifier(getSHA256(request.getPan()));

        cardRepository.save(entity);

        response.setResponseCode(ResponseCodes.SUCCESS);
        response.setHashIdentifier(entity.getHashIdentifier());
        response.setValidationCode(entity.getValidationCode());
        response.setPan(maskPan(entity.getPan()));

        return response;
    }

    @Override
    public CardValidationResponse validate(CardValidationRequest request) {
        CardValidationResponse response = new CardValidationResponse();

        CardEntity entity = cardRepository.findByHashIdentifierIs(request.getHashIdentifier());

        if (entity == null) {
            throw new CardNotFoundException();
        } else if(!request.getValidationCode().equals(entity.getValidationCode())) {
            throw new InvalidVerificationCodeException();
        } else {
            response.setResponseCode(ResponseCodes.SUCCESS);
            entity.setStatus(CardStatus.ENROLADA);
            cardRepository.save(entity);
        }

        return response;
    }

    @Override
    public CardResponse consult(String hashIdentifier) {
        CardResponse response = new CardResponse();

        CardEntity entity = cardRepository.findByHashIdentifierIs(hashIdentifier);

        if (entity == null) {
            throw new CardNotFoundException();
        } else {
            response.setResponseCode(ResponseCodes.SUCCESS);
            response.setPan(maskPan(entity.getPan()));
            response.setCustomerId(entity.getCustomerId());
            response.setCustomerName(entity.getCustomerName());
            response.setCustomerPhone(entity.getCustomerPhone());
        }

        return response;
    }

    @Override
    public List<CardResponse> findAll() {
        List<CardEntity> cards = cardRepository.findAll();
        List<CardResponse> cardsList = new ArrayList<>();

        cards.stream().forEach( x -> {
            CardResponse card = new CardResponse();
            card.setCustomerPhone(x.getCustomerPhone());
            card.setCustomerName(x.getCustomerName());
            card.setCustomerId(x.getCustomerId());
            card.setPan(maskPan(x.getPan()));
            card.setHashIdentifier(x.getHashIdentifier());
            card.setType(x.getType());
            cardsList.add(card);
        });

        return cardsList;
    }

    @Override
    public BaseResponse deleteCard(CardDeleteRequest request) {
        BaseResponse response = new BaseResponse();

        CardEntity entity = cardRepository.findByHashIdentifierIs(request.getHashIdentifier());

        if (entity == null) {
            throw new CardNotFoundException();
        } else {
            response.setResponseCode(ResponseCodes.SUCCESS);
            entity.setStatus(CardStatus.INACTIVA);
            cardRepository.save(entity);
        }

        return response;
    }

    private int  getCode() {
        Random rn = new Random();
        return rn.nextInt(100) + 1;
    }

    private String getSHA256(String input){
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-ddHHmmss");

        input = input + format.format(new Date());

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    private String maskPan(String pan) {
        int prefixVisibleCount = 6;
        int suffixVisibleCount = 4;
        String toReplace = "";

        if (pan.length() < prefixVisibleCount + suffixVisibleCount) {
            return pan;
        } else if (pan.length() == prefixVisibleCount + suffixVisibleCount) {
            toReplace = pan.substring(0,prefixVisibleCount);
        } else {
            toReplace = pan.substring(prefixVisibleCount, (pan.length() - (prefixVisibleCount + suffixVisibleCount)) + prefixVisibleCount);
        }

        String replacer = Arrays.stream(toReplace.split("")).map(x -> "*").collect(Collectors.joining());
        return pan.replaceFirst(toReplace, replacer);
    }
}
