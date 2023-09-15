package com.example.apipagos.service;

import com.example.apipagos.domain.entity.ExchangeRate;
import com.example.apipagos.dto.SaveExchangeRateDto;
import com.example.apipagos.dto.UpdateExchangeRateDto;

public interface ExchangeRateService {
    ExchangeRate saveExchangeRate(SaveExchangeRateDto saveExchangeRateDto);

    ExchangeRate updateExchangeRate(Long id, UpdateExchangeRateDto updateExchangeRateDto);
}
