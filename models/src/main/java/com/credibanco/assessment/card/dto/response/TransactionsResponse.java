package com.credibanco.assessment.card.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionsResponse extends BaseResponse {
    private List<TransactionResponse> transactions;
}
