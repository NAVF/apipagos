package com.example.apipagos.dto;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class UpdateExchangeRateDto implements Serializable {
    private static final long serialVersionUID = -5926603707272090939L;

    private Float exchangeRate;
}
