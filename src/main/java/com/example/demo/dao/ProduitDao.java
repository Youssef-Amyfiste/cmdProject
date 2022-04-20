package com.example.demo.dao;

import com.example.demo.bean.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitDao extends JpaRepository<Produit, Long> {
    Produit findByCode(String code);

}

