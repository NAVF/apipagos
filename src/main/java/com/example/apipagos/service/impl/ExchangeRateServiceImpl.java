package com.example.apipagos.service.impl;

import com.example.apipagos.domain.entity.ExchangeRate;
import com.example.apipagos.dto.SaveExchangeRateDto;
import com.example.apipagos.dto.UpdateExchangeRateDto;
import com.example.apipagos.exceptions.CatalogErrors;
import com.example.apipagos.repository.ExchangeRateRepository;
import com.example.apipagos.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public ExchangeRate saveExchangeRate(SaveExchangeRateDto saveExchangeRateDto) {
        if(
            exchangeRateRepository
                    .findByMoneyOriginAndMoneyDestiny(
                            saveExchangeRateDto.getMoneyOrigin(),
                            saveExchangeRateDto.getMoneyDestiny())
                    .isPresent()
        ) throw CatalogErrors.PAY_EXCHANGE_RATE_E400_001.getException();

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setMoneyOrigin(saveExchangeRateDto.getMoneyOrigin());
        exchangeRate.setMoneyDestiny(saveExchangeRateDto.getMoneyDestiny());
        exchangeRate.setExchangeRate(saveExchangeRateDto.getExchangeRate());

        return exchangeRateRepository.save(exchangeRate);
    }

    @Override
    public ExchangeRate updateExchangeRate(Long id, UpdateExchangeRateDto updateExchangeRateDto) {
        Optional<ExchangeRate> optExchangeRate = exchangeRateRepository.findById(id);

        if (optExchangeRate.isEmpty()){
            throw CatalogErrors.PAY_EXCHANGE_RATE_E404_001.getException();
        } else {
            ExchangeRate exchangeRate = optExchangeRate.get();
            exchangeRate.setExchangeRate(updateExchangeRateDto.getExchangeRate());
            return exchangeRateRepository.save(exchangeRate);
        }
    }
}
