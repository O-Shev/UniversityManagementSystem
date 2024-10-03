package com.leshkins.universitymanagementsystem.service;

import com.leshkins.universitymanagementsystem.model.Lector;
import com.leshkins.universitymanagementsystem.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectorService {
    private final LectorRepository lectorRepository;

    @Autowired
    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public List<String> globalSearchByTemplate(String template){
        return lectorRepository.findByNameContainingIgnoreCase(template).stream().map(Lector::getName).toList();
    }
}
