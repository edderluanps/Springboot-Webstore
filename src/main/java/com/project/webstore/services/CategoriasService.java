package com.project.webstore.services;

import com.project.webstore.domains.Categorias;
import com.project.webstore.services.exception.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.webstore.repositories.CategoriasRepository;

@Service
public class CategoriasService {
    
    @Autowired
    private CategoriasRepository catrepo;
    
    public Categorias find(Integer id) {
        Optional<Categorias> obj = catrepo.findById(id);
        
        return obj.orElseThrow(() -> new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo: " + Categorias.class.getName()));
    }
    
    public Categorias insert(Categorias obj){
        return catrepo.save(obj);
    }
    
    public Categorias update(Categorias obj){
        find(obj.getId());
        return catrepo.save(obj);
    }
}
