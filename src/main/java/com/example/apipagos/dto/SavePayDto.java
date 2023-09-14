package com.example.apipagos.dto;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class SavePayDto implements Serializable {
    private static final long serialVersionUID = 7452932643012033766L;

    private Float amount;
    private String moneyOrigin;
    private String moneyDestiny;
    private Long userId;
}
