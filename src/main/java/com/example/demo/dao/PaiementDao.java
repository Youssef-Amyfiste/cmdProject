package com.example.demo.dao;

import com.example.demo.bean.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementDao extends JpaRepository<Paiement, Long> {
    Paiement findByCode(String code);

    int deleteByCode(String code);

    int deleteByCommandeRef(String ref);

    List<Paiement> findByCommandeRef(String ref);

    Paiement findByCommandeRefAndCode(String ref, String code);
}
