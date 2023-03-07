package com.credibanco.assessment.card.repository;

import com.credibanco.assessment.card.model.CardEntity;
import com.credibanco.assessment.card.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long> {
    TransactionEntity findOneByCardAndReferenceNumberAndBuyAmount(CardEntity card, String referenceNumber, Double buyAmount);

    List<TransactionEntity> findAllByCard(CardEntity card);
}
