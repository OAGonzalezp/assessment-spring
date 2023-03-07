package com.credibanco.assessment.card.model;

import com.credibanco.assessment.card.dto.enums.CardStatus;
import com.credibanco.assessment.card.dto.enums.CardType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "card")
public class CardEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String pan;
    private String customerName;
    private String customerId;
    private String customerPhone;
    private Integer validationCode;
    @Enumerated(EnumType.STRING)
    private CardType type;
    @Enumerated(EnumType.STRING)
    private CardStatus status;
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TransactionEntity> transactions;

}
