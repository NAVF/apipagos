package com.example.apipagos.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter
@ToString
public class SavedPayDto implements Serializable {
    private static final long serialVersionUID = -7378581899720784256L;

    private Long payId;
    private Float amount;
    private Float amountConverted;
    private String moneyOrigin;
    private String moneyDestiny;
    private Float exchangeRate;
}
