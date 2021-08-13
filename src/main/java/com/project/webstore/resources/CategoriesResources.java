package com.project.webstore.resources;

import com.project.webstore.domains.Categories;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesResources {
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Categories> listar(){
        
        Categories cat1 = new Categories(null, "Informatica");
        Categories cat2 = new Categories(null, "Escritorio");
               
        List<Categories> lista = new ArrayList<>();
        lista.add(cat1);
        lista.add(cat2);
        
        return lista;
    }
    
}
