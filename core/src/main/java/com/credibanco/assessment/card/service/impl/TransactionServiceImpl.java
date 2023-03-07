package com.credibanco.assessment.card.service.impl;

import com.credibanco.assessment.card.dto.request.TransactionCancelRequest;
import com.credibanco.assessment.card.dto.request.TransactionCreationRequest;
import com.credibanco.assessment.card.dto.response.TransactionCancelResponse;
import com.credibanco.assessment.card.dto.response.TransactionCreationResponse;
import com.credibanco.assessment.card.dto.response.TransactionsResponse;
import com.credibanco.assessment.card.dao.ITransactionDao;
import com.credibanco.assessment.card.model.TransactionEntity;
import com.credibanco.assessment.card.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    ITransactionDao transactionDao;

    @Override
    public TransactionCreationResponse save(TransactionCreationRequest request) {
        return transactionDao.save(request);
    }

    @Override
    public List<TransactionEntity> findAll() {
        return transactionDao.findAll();
    }

    @Override
    public TransactionCancelResponse cancelTransaction(TransactionCancelRequest request) {
        return transactionDao.cancelTransaction(request);
    }

    @Override
    public TransactionsResponse findAll(String pan) {
        return transactionDao.findAll(pan);
    }
}
