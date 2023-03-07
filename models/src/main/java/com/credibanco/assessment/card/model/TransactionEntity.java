package com.credibanco.assessment.card.model;

import com.credibanco.assessment.card.dto.enums.TransactionStatus;
import com.credibanco.assessment.card.model.CardEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String referenceNumber;
    @ManyToOne
    private CardEntity card;
    private Double buyAmount;
    private String buyAddress;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
