package com.example.apipagos.repository;

import com.example.apipagos.domain.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay,Long> {
}
