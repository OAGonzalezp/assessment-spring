package com.credibanco.assessment.card.dao.impl;

import com.credibanco.assessment.card.dao.ITransactionDao;
import com.credibanco.assessment.card.dto.enums.ResponseCodes;
import com.credibanco.assessment.card.exceptions.CardNotFoundException;
import com.credibanco.assessment.card.exceptions.CardNotVerifiedException;
import com.credibanco.assessment.card.exceptions.TransactionNotCanceledException;
import com.credibanco.assessment.card.exceptions.TransactionNotFoundException;
import com.credibanco.assessment.card.model.CardEntity;
import com.credibanco.assessment.card.model.TransactionEntity;
import com.credibanco.assessment.card.dto.enums.CardStatus;
import com.credibanco.assessment.card.dto.enums.TransactionStatus;
import com.credibanco.assessment.card.dto.request.TransactionCancelRequest;
import com.credibanco.assessment.card.dto.request.TransactionCreationRequest;
import com.credibanco.assessment.card.dto.response.TransactionCancelResponse;
import com.credibanco.assessment.card.dto.response.TransactionCreationResponse;
import com.credibanco.assessment.card.dto.response.TransactionResponse;
import com.credibanco.assessment.card.dto.response.TransactionsResponse;
import com.credibanco.assessment.card.repository.ICardRepository;
import com.credibanco.assessment.card.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionDaoImpl implements ITransactionDao {

    @Autowired
    ITransactionRepository repository;
    @Autowired
    ICardRepository cardRepository;

    @Override
    public TransactionCreationResponse save(TransactionCreationRequest request) {
        TransactionCreationResponse response = new TransactionCreationResponse();
        TransactionEntity entity = new TransactionEntity();

        CardEntity card = cardRepository.findByPanIs(request.getPan());

        if (card == null) {
            throw new CardNotFoundException();
        }

        if (!CardStatus.ENROLADA.equals(card.getStatus())) {
            throw new CardNotVerifiedException();
        }

        entity.setReferenceNumber(request.getReferenceNumber());
        entity.setBuyAmount(request.getBuyAmount());
        entity.setBuyAddress(request.getBuyAddress());
        entity.setCard(card);
        entity.setDate(new Date());
        entity.setStatus(TransactionStatus.APROBADA);
        repository.save(entity);

        response.setResponseCode(ResponseCodes.SUCCESS);
        response.setStatus(entity.getStatus());
        response.setReferenceNumber(entity.getReferenceNumber());

        return response;
    }

    @Override
    public List<TransactionEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public TransactionsResponse findAll(String pan) {

        TransactionsResponse response = new TransactionsResponse();
        CardEntity card = cardRepository.findByPanIs(pan);

        if (card == null) {
            throw new CardNotFoundException();
        }

        response.setResponseCode(ResponseCodes.SUCCESS);
        response.setTransactions(repository.findAllByCard(card).stream().map( x -> new TransactionResponse(x.getReferenceNumber(),
                x.getBuyAmount(), x.getBuyAddress(), x.getStatus())).collect(Collectors.toList()));


        return response;
    }

    @Override
    public TransactionCancelResponse cancelTransaction(TransactionCancelRequest request) {
        TransactionCancelResponse response = new TransactionCancelResponse();

        CardEntity card = cardRepository.findByPanIs(request.getPan());

        if (card == null) {
            throw new CardNotFoundException();
        }

        TransactionEntity entity = repository.findOneByCardAndReferenceNumberAndBuyAmount(card, request.getReferenceNumber(), request.getBuyAmount());

        if (entity == null) {
            throw new TransactionNotFoundException();
        }
        long diff = new Date().getTime() - entity.getDate().getTime();
        long diffMinutes = diff / (60 * 1000) % 60;

        if (diffMinutes < 5) {
            throw new TransactionNotCanceledException();
        }

        entity.setStatus(TransactionStatus.RECHAZADA);
        repository.save(entity);

        response.setResponseCode(ResponseCodes.SUCCESS);
        response.setReferenceNumber(entity.getReferenceNumber());

        return response;
    }
}
