package com.example.apipagos.client.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseGetBalancesUserDto {
    private Long id;
    private Float balance;
    private Long userId;
}
