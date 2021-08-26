package com.project.webstore.services;

import com.project.webstore.domains.Produtos;
import com.project.webstore.services.exception.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.webstore.repositories.ProdutosRepository;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository pedidosRepo;

    public Produtos buscar(Integer id) {
        Optional<Produtos> obj = pedidosRepo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo: " + Produtos.class.getName()));
    }
}
