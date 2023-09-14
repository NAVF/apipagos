package com.example.apipagos.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "ma_exchange_rate")
public class ExchangeRate implements Serializable {

    private static final long serialVersionUID = 8840867215787480453L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moneyOrigin;

    private String moneyDestiny;

    private Float exchangeRate;
}
