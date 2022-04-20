package com.example.demo.dao;

import com.example.demo.bean.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePaiementDao extends JpaRepository<TypePaiement, Long> {
    TypePaiement findByCode(String code);

}

