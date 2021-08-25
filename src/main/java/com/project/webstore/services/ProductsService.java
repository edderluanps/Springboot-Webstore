package com.project.webstore.services;

import com.project.webstore.domains.Products;
import com.project.webstore.repositories.ProductsRepository;
import com.project.webstore.services.exception.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository pedidosRepo;

    public Products buscar(Integer id) {
        Optional<Products> obj = pedidosRepo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo: " + Products.class.getName()));
    }
}
