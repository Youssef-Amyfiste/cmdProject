package com.example.demo.service.impl;

import com.example.demo.bean.Commande;
import com.example.demo.bean.CommandeItem;
import com.example.demo.bean.Paiement;
import com.example.demo.dao.CommandeDao;
import com.example.demo.dao.ProduitDao;
import com.example.demo.service.facade.CommandeItemService;
import com.example.demo.service.facade.CommandeService;
import com.example.demo.service.facade.PaiementService;
import com.example.demo.service.facade.ProduitService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements CommandeService {
    @Autowired
    private CommandeDao commandeDao;
    @Autowired
    private PaiementService paiementService;
    @Autowired
    private CommandeItemService commandeItemService;
    @Autowired
    private ProduitService produitService;


    public String exportReport (String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\hp\\AReports";
        List<Commande> commandes = commandeDao.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:commandeList.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(commandes);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\commandes.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\commandes.pdf");
        }

        return "report generated in path : " + path;
    }

    @Override
    public List<Commande> findAll() {
        return commandeDao.findAll();
    }


    @Override
    public void update(Commande commande) {
        if (findByRef(commande.getRef()) != null)
            commandeDao.save(commande);
    }

    @Override
    public int save(Commande commande) {
        int res = validateSave(commande);
        if (res < 0)
            return res;
        else {
            commandeDao.save(commande);
            commande.getCommandeItems().forEach(e -> {
                e.setCommande(commande);
                e.setProduit(produitService.findByCode(e.getProduit().getCode()));
                commandeItemService.save(e);
            });

            return 1;
        }
    }

    private int validateSave(Commande commande) {
        if (findByRef(commande.getRef()) != null) {
            return -1;
    /*    } else if (commande.getCommandeItems() == null || commande.getCommandeItems().isEmpty())
            return -2;
        else {

            List<CommandeItem> commandeItems = commande.getCommandeItems().stream()
                    .filter(e -> e.getProduit() != null && e.getProduit().getCode() != null && produitService.findByCode(e.getProduit().getCode()) == null).
                    collect(Collectors.toList());

//            List<CommandeItem> commandeItems = findCommandeItemOfNonExistedProduit(commande);
            if (commandeItems == null && commandeItems.isEmpty()) {
                return -3; */
            } else {
                return 1;
            }
        }


    private List<CommandeItem> findCommandeItemOfNonExistedProduit(Commande commande) {
        List<CommandeItem> res = new ArrayList<>();
        for (CommandeItem commandeItem : commande.getCommandeItems()) {
            if (produitService.findByCode(commandeItem.getProduit().getCode()) == null)
                res.add(commandeItem);
        }
        return res;
    }

    @Override
    public Commande findByRef(String ref) {
        return commandeDao.findByRef(ref);
    }

    @Override
    @Transactional
    public int deleteWithAssociatedPaiments(String ref) {
        int res1 = paiementService.deleteByCommandeRef(ref);
        int res2 = commandeItemService.deleteByCommandeRef(ref);
        int res3 = commandeDao.deleteByRef(ref);
        return res1 + res2 + res3;

    }

    @Override
    @Transactional
    public int deleteByRef(String ref) {
        return commandeDao.deleteByRef(ref);
    }
}
