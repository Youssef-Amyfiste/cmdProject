package com.example.demo.service.facade;

import com.example.demo.bean.Paiement;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface PaiementService {
    int save(Paiement paiement);

    Paiement findByCode(String code);

    int deleteByCommandeRef(String ref);

    List<Paiement> findByCommandeRef(String ref);
    List<Paiement> findAll();


    Paiement findByCommandeRefAndCode(String ref, String code);

    String exportReport(String format) throws FileNotFoundException, JRException;
}
