package com.project.webstore.services;

import com.project.webstore.domains.Categorias;
import com.project.webstore.services.exception.ObjectNotFoundException;
import com.project.webstore.services.exception.DataIntegrityException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.webstore.repositories.CategoriasRepository;
import org.springframework.dao.DataIntegrityViolationException;

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

    public void delete(Integer id){
        find(id);
        try {
            catrepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possíve excluir: Categoria preenchida!");
        }
    }
}
