package com.example.apipagos.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class RequestUpdateBalanceDto {
    private Float amount;
}
