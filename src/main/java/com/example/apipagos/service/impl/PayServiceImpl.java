package com.example.apipagos.service.impl;

import com.example.apipagos.client.ApiSaldosFeignClient;
import com.example.apipagos.client.dto.RequestUpdateBalanceDto;
import com.example.apipagos.client.dto.ResponseGetBalancesUserDto;
import com.example.apipagos.client.dto.ResponseUpdateBalanceDto;
import com.example.apipagos.domain.entity.ExchangeRate;
import com.example.apipagos.domain.entity.Pay;
import com.example.apipagos.domain.type.StatusPay;
import com.example.apipagos.dto.SavePayDto;
import com.example.apipagos.dto.SavedPayDto;
import com.example.apipagos.exceptions.CatalogErrors;
import com.example.apipagos.repository.ExchangeRateRepository;
import com.example.apipagos.repository.PayRepository;
import com.example.apipagos.service.PayService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final ExchangeRateRepository exchangeRateRepository;

    private final PayRepository payRepository;

    private final ApiSaldosFeignClient apiSaldosFeignClient;


    @Override
    public SavedPayDto savePay(SavePayDto savePayDto) {
        ExchangeRate exchangeRate = this.getExchangeRate(savePayDto);
        Pay pay = this.registerPay(savePayDto,exchangeRate);
        this.updateBalance(pay);
        return this.responseService(pay);
    }

    private ExchangeRate getExchangeRate(SavePayDto savePayDto){
        log.info("Inicializando conversion de divisa.");
        Optional<ExchangeRate> optExchangeRate = exchangeRateRepository.findByMoneyOriginAndMoneyDestiny(savePayDto.getMoneyOrigin(), savePayDto.getMoneyDestiny());
        if (optExchangeRate.isEmpty()) throw CatalogErrors.PAY_EXCHANGE_RATE_E404_001.getException();

        return optExchangeRate.get();
    }

    private Pay registerPay(SavePayDto savePayDto, ExchangeRate exchangeRate){
        log.info("Registro de pago.");
        Pay pay = new Pay();
        pay.setAmount(savePayDto.getAmount());
        pay.setAmountConverted(savePayDto.getAmount() * exchangeRate.getExchangeRate());
        pay.setMoneyOrigin(exchangeRate.getMoneyOrigin());
        pay.setMoneyDestiny(exchangeRate.getMoneyDestiny());
        pay.setExchangeRate(exchangeRate.getExchangeRate());
        pay.setUserId(savePayDto.getUserId());
        pay.setStatus(StatusPay.ACEPTED);
        payRepository.save(pay);

        return pay;
    }

    private SavedPayDto responseService(Pay pay){
        log.info("Preparando objeto de retorno del servicio.");
        SavedPayDto savedPayDto =
                SavedPayDto.builder()
                        .payId(pay.getId())
                        .amount(pay.getAmount())
                        .amountConverted(pay.getAmountConverted())
                        .moneyOrigin(pay.getMoneyOrigin())
                        .moneyDestiny(pay.getMoneyDestiny())
                        .exchangeRate(pay.getExchangeRate())
                        .build();

        return savedPayDto;
    }

    private void updateBalance(Pay pay){
        try {
            log.info("Invocando servicio de Saldo por id de usuario: {}",pay.getUserId());
            ResponseGetBalancesUserDto responseGetBalancesUserDto = apiSaldosFeignClient.getBalancesUserDto(pay.getUserId());
            log.info("Respuesta de API, saldo de usuario: {}", responseGetBalancesUserDto.toString());

            RequestUpdateBalanceDto requestUpdateBalanceDto = RequestUpdateBalanceDto.builder().amount(pay.getAmountConverted()).build();
            log.info("Invocando servicio de actualización de saldo, objeto enviado: {}", requestUpdateBalanceDto);
            ResponseUpdateBalanceDto responseUpdateBalanceDto = apiSaldosFeignClient.updateBalance(responseGetBalancesUserDto.getId(), requestUpdateBalanceDto);
            log.info("Respuesta servicio de actualización de saldo: {}", responseUpdateBalanceDto);

        } catch (Exception e){
            log.error("Error al Invocar servicio de actualización de saldo. {}",e.getMessage());
            pay.setStatus(StatusPay.RETORNED);
            payRepository.save(pay);
            throw CatalogErrors.PAY_SAVE_PAY_E500_001.getException();
        }
    }
}
