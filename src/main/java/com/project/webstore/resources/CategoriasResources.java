package com.project.webstore.resources;

import com.project.webstore.domains.Categorias;
import com.project.webstore.dto.CategoriasDTO;
import com.project.webstore.services.CategoriasService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriasDTO objDTO) {
        Categorias obj = catservice.fromDTO(objDTO);
        obj = catservice.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriasDTO objDTO, @PathVariable Integer id){
        Categorias obj = catservice.fromDTO(objDTO);
        obj.setId(id);
        obj = catservice.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        catservice.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity <Page<CategoriasDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page, 
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy, 
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Categorias> list = catservice.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriasDTO> listDTO = list.map(obj -> new CategoriasDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }
}
