package com.credibanco.assessment.card.dao;

import com.credibanco.assessment.card.model.TransactionEntity;
import com.credibanco.assessment.card.dto.request.TransactionCancelRequest;
import com.credibanco.assessment.card.dto.request.TransactionCreationRequest;
import com.credibanco.assessment.card.dto.response.TransactionCancelResponse;
import com.credibanco.assessment.card.dto.response.TransactionCreationResponse;
import com.credibanco.assessment.card.dto.response.TransactionsResponse;

import java.util.List;

public interface ITransactionDao {

    TransactionCreationResponse save(TransactionCreationRequest request);
    List<TransactionEntity> findAll();
    TransactionCancelResponse cancelTransaction(TransactionCancelRequest request);
    TransactionsResponse findAll(String pan);
}
