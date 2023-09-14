package com.example.apipagos.service;

import com.example.apipagos.dto.SavePayDto;
import com.example.apipagos.dto.SavedPayDto;

public interface PayService {
    SavedPayDto savePay(SavePayDto savePayDto);
}
