package com.example.demo.service.impl;

import com.example.demo.bean.TypePaiement;
import com.example.demo.dao.TypePaiementDao;
import com.example.demo.service.facade.TypePaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class
TypePaiementServiceImpl implements TypePaiementService {
    @Autowired
    private TypePaiementDao typePaiementDao;


    @Override
    public TypePaiement findByCode(String code) {
        return typePaiementDao.findByCode(code);
    }
}
