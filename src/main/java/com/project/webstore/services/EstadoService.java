package com.project.webstore.services;

import com.project.webstore.domains.Estado;
import com.project.webstore.repositories.EstadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repo;

    public List<Estado> findAll() {
        return repo.findAllByOrderByNome();
    }
}
