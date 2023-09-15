package com.example.apipagos.dto;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class SaveExchangeRateDto implements Serializable {
    private static final long serialVersionUID = 2831382635699664368L;

    private String moneyOrigin;
    private String moneyDestiny;
    private Float exchangeRate;
}
