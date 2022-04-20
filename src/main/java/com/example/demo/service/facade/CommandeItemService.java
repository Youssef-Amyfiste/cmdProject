package com.example.demo.service.facade;

import com.example.demo.bean.CommandeItem;

import java.util.List;

public interface CommandeItemService {
    List<CommandeItem> findByCommandeRef(String ref);

    int deleteByCommandeRef(String ref);

    void save(CommandeItem commandeItem);
}
