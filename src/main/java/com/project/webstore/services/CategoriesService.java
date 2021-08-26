package com.project.webstore.services;

import com.project.webstore.domains.Categories;
import com.project.webstore.repositories.CategoriesRepository;
import com.project.webstore.services.exception.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {
    
    @Autowired
    private CategoriesRepository catrepo;
    
    public Categories buscar(Integer id) {
        Optional<Categories> obj = catrepo.findById(id);
        
        return obj.orElseThrow(() -> new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo: " + Categories.class.getName()));
    }
    
    public Categories insert(Categories obj){
        return catrepo.save(obj);
    }
    
}
