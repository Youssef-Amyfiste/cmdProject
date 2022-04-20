package com.example.demo.service.facade;

import com.example.demo.bean.Commande;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface CommandeService {
    void update(Commande commande);

    int save(Commande commande);

    Commande findByRef(String ref);
    int deleteWithAssociatedPaiments(String ref);
    int deleteByRef(String ref);

    String exportReport(String format) throws FileNotFoundException, JRException;

    List<Commande> findAll();
}
