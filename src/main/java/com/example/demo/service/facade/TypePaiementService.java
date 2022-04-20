package com.example.demo.service.facade;

import com.example.demo.bean.TypePaiement;

public interface TypePaiementService {
    TypePaiement findByCode(String code);
}
