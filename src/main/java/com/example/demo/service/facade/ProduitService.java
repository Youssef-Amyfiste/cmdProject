package com.example.demo.service.facade;

import com.example.demo.bean.Produit;

public interface ProduitService {
    Produit findByCode(String code);
}
