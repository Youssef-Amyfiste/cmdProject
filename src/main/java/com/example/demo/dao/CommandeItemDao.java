package com.example.demo.dao;

import com.example.demo.bean.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeItemDao extends JpaRepository<CommandeItem, Long> {
    List<CommandeItem> findByCommandeRef(String ref);

    int deleteByCommandeRef(String ref);
}
