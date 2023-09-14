package com.example.apipagos.controller;

import com.example.apipagos.domain.entity.ExchangeRate;
import com.example.apipagos.dto.SaveExchangeRateDto;
import com.example.apipagos.dto.SavePayDto;
import com.example.apipagos.dto.SavedPayDto;
import com.example.apipagos.dto.UpdateExchangeRateDto;
import com.example.apipagos.service.ExchangeRateService;
import com.example.apipagos.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PagoController {

    private final PayService payService;

    private final ExchangeRateService exchangeRateService;

    @PostMapping("/pays")
    public SavedPayDto savePay(@RequestBody SavePayDto savePayDto){
        return payService.savePay(savePayDto);
    }

    @PostMapping("/exchange-rates")
    public ResponseEntity<ExchangeRate> saveExchangeRate(@RequestBody SaveExchangeRateDto saveExchangeRateDto){
        return new ResponseEntity<>(exchangeRateService.saveExchangeRate(saveExchangeRateDto),HttpStatus.CREATED);
    }

    @PutMapping("/exchange-rates/{id}")
    public ResponseEntity<ExchangeRate> updateExchangeRate(@PathVariable Long id, @RequestBody UpdateExchangeRateDto updateExchangeRateDto){
        return new ResponseEntity<>(exchangeRateService.updateExchangeRate(id, updateExchangeRateDto),HttpStatus.ACCEPTED);
    }
}
