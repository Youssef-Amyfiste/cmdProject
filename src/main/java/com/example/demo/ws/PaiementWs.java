package com.example.demo.ws;

import com.example.demo.bean.Paiement;
import com.example.demo.service.facade.CommandeService;
import com.example.demo.service.facade.PaiementService;
import com.example.demo.service.impl.PaiementServiceImpl;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/paiement")
public class PaiementWs {



    @Autowired
    private PaiementService paiementService;

    @GetMapping("/")
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Paiement paiement) {
        return paiementService.save(paiement);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByCommandeRef(@PathVariable String ref) {
        return paiementService.deleteByCommandeRef(ref);
    }
    @GetMapping("/commande/ref/{ref}")
    public List<Paiement> findByCommandeRef(String ref) {
        return paiementService.findByCommandeRef(ref);
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return paiementService.exportReport(format);
    }
}
