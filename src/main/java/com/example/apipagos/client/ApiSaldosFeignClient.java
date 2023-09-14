package com.example.apipagos.client;

import com.example.apipagos.client.dto.RequestUpdateBalanceDto;
import com.example.apipagos.client.dto.ResponseGetBalancesUserDto;
import com.example.apipagos.client.dto.ResponseUpdateBalanceDto;
import com.example.apipagos.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "apiSaldosFeignClient", url = "${external.apisaldos.base-url}", configuration = FeignConfig.class)
public interface ApiSaldosFeignClient {

    @GetMapping("/balances/{userId}")
    ResponseGetBalancesUserDto getBalancesUserDto(@PathVariable Long userId);

    @PutMapping("balances/{id}/update-balance")
    ResponseUpdateBalanceDto updateBalance(@PathVariable Long id, RequestUpdateBalanceDto requestUpdateBalanceDto);
}
