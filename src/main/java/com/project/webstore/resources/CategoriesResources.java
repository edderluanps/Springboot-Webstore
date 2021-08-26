package com.project.webstore.resources;

import com.project.webstore.domains.Categories;
import com.project.webstore.services.CategoriesService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesResources {
    
    @Autowired
    private CategoriesService catservice;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Categories obj = catservice.buscar(id);
        return ResponseEntity.ok().body(obj);
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categories obj) {
        obj = catservice.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
        
    }
    
}
