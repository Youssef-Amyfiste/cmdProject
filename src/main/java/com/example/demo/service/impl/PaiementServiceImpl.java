package com.example.demo.service.impl;

import com.example.demo.bean.Commande;
import com.example.demo.bean.Paiement;
import com.example.demo.bean.TypePaiement;
import com.example.demo.dao.PaiementDao;
import com.example.demo.service.facade.CommandeService;
import com.example.demo.service.facade.PaiementService;
import com.example.demo.service.facade.TypePaiementService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaiementServiceImpl implements PaiementService {
    @Override
    public List<Paiement> findAll() {
        return paiementDao.findAll();
    }

    @Autowired
    private PaiementDao paiementDao;
    @Autowired
    private CommandeService commandeService;
    @Autowired
    private TypePaiementService typePaiementService;


    public String exportReport (String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\hp\\AReports";
        List<Paiement> paiements = paiementDao.findAll();
       // List<Commande> commandes = commandeService.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:paiments.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(paiements);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\paiements.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\paiements.pdf");
        }

        return "report generated in path : " + path;
    }






    @Override
    public int save(Paiement paiement) {
        Commande commande = commandeService.findByRef(paiement.getCommande().getRef());
        paiement.setCommande(commande);
        TypePaiement typePaiement = typePaiementService.findByCode(paiement.getTypePaiement().getCode());
        paiement.setTypePaiement(typePaiement);
        if (typePaiement == null) {
            return -1;
        } else if (commande == null) {
            return -2;
        } else if (commande.getTotalPaye() + paiement.getMontant() > commande.getTotal())
            return -3;
        else {
            paiementDao.save(paiement);
            commande.setTotalPaye(commande.getTotalPaye() + paiement.getMontant());
            commandeService.update(commande);
            return 1;
        }

    }

    @Override
    public Paiement findByCode(String code) {
        return paiementDao.findByCode(code);
    }

    @Override
    public int deleteByCommandeRef(String ref) {
        return paiementDao.deleteByCommandeRef(ref);
    }

    @Override
    public List<Paiement> findByCommandeRef(String ref) {
        return paiementDao.findByCommandeRef(ref);
    }

    @Override
    public Paiement findByCommandeRefAndCode(String ref, String code) {
        return paiementDao.findByCommandeRefAndCode(ref,code);
    }
}
