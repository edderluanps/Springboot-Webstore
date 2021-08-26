package com.project.webstore.resources;

import com.project.webstore.domains.Pedidos;
import com.project.webstore.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidosResources {
    
    @Autowired
    private PedidoService pedidoService;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedidos> find(@PathVariable Integer id){
        Pedidos obj = pedidoService.find(id);
        return ResponseEntity.ok().body(obj);
        
    }
    
}
