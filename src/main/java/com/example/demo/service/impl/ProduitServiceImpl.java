package com.example.demo.service.impl;

import com.example.demo.bean.Produit;
import com.example.demo.dao.ProduitDao;
import com.example.demo.service.facade.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduitServiceImpl implements ProduitService {
    @Override
    public Produit findByCode(String code) {
        return produitDao.findByCode(code);
    }

    @Autowired
    private ProduitDao produitDao;
}
