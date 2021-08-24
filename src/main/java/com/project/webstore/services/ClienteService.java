package com.project.webstore.services;

import com.project.webstore.domains.Cliente;
import com.project.webstore.repositories.ClienteRepository;
import com.project.webstore.services.exception.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    public Cliente buscar(Integer id) {
        Optional<Cliente> obj = clienteRepo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
}
