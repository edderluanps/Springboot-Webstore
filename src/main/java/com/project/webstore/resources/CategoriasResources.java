package com.project.webstore.resources;

import com.project.webstore.domains.Categorias;
import com.project.webstore.dto.CategoriasDTO;
import com.project.webstore.services.CategoriasService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriasResources {
    
    @Autowired
    private CategoriasService catservice;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity <List<CategoriasDTO>> findAll(){
        List<Categorias> list = catservice.findAll();
        List<CategoriasDTO> listDTO = list.stream().map(obj -> new CategoriasDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categorias> find(@PathVariable Integer id){
        Categorias obj = catservice.find(id);
        return ResponseEntity.ok().body(obj);
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categorias obj) {
        obj = catservice.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Categorias obj, @PathVariable Integer id){
        obj.setId(id);
        obj = catservice.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        catservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
