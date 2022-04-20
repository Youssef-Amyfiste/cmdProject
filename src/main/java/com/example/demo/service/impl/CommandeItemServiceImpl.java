package com.example.demo.service.impl;

import com.example.demo.bean.CommandeItem;
import com.example.demo.dao.CommandeItemDao;
import com.example.demo.dao.ProduitDao;
import com.example.demo.service.facade.CommandeItemService;
import com.example.demo.service.facade.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeItemServiceImpl implements CommandeItemService {
    @Autowired
    private CommandeItemDao commandeItemDao;


    @Override
    public List<CommandeItem> findByCommandeRef(String ref) {
        return commandeItemDao.findByCommandeRef(ref);
    }
    @Override
    public int deleteByCommandeRef(String ref) {
        return commandeItemDao.deleteByCommandeRef(ref);
    }

    @Override
    public void save(CommandeItem commandeItem){
        commandeItemDao.save(commandeItem);
    }
}
