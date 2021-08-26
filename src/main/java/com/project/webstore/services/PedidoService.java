package com.project.webstore.services;

import com.project.webstore.domains.Pedidos;
import com.project.webstore.repositories.PedidoRepository;
import com.project.webstore.services.exception.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidosRepo;

    public Pedidos find(Integer id) {
        Optional<Pedidos> obj = pedidosRepo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo: " + Pedidos.class.getName()));
    }
}
