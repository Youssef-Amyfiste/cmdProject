package com.example.demo.ws;

import com.example.demo.bean.Commande;
import com.example.demo.service.facade.CommandeService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/commande")
public class CommandeWs {
   @PostMapping("/")
    public int save(@RequestBody Commande commande) {
        return commandeService.save(commande);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteWithAssociatedPaiments(@PathVariable String ref) {
        return commandeService.deleteWithAssociatedPaiments(ref);
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return commandeService.exportReport(format);
    }

    @GetMapping("/")
    public List<Commande> findAll() {
        return commandeService.findAll();
    }

    @Autowired
    private CommandeService commandeService;




}
