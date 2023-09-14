package com.example.apipagos.repository;

import com.example.apipagos.domain.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findByMoneyOriginAndMoneyDestiny(String moneyOrigin, String moneyDestiny);
}
