package com.credibanco.assessment.card.model;

import com.credibanco.assessment.card.dto.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Date date;
    @ManyToOne
    private CardEntity card;
    private Double buyAmount;
    private String buyAddress;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
