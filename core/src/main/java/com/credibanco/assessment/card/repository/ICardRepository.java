package com.credibanco.assessment.card.repository;

import com.credibanco.assessment.card.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends JpaRepository<CardEntity, Long> {
    CardEntity findByPanIs(String pan);
}
