package com.example.apipagos.domain.entity;

import com.example.apipagos.domain.type.StatusPay;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tr_pay")
public class Pay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float amount;
    private Float amountConverted;
    private String moneyOrigin;
    private String moneyDestiny;
    private Float exchangeRate;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private StatusPay status;
}
