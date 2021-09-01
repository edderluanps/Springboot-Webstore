package com.project.webstore.services;

import com.project.webstore.domains.Cliente;
import com.project.webstore.domains.enums.TipoCliente;
import com.project.webstore.dto.ClienteDTO;
import com.project.webstore.repositories.ClienteRepository;
import com.project.webstore.services.exception.DataIntegrityException;
import com.project.webstore.services.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;
    
    public List<Cliente> findAll() {
        return clienteRepo.findAll();
    }
    
    public Cliente find(Integer id) {
        Optional<Cliente> obj = clienteRepo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
    
    public Cliente insert(Cliente obj){
        return clienteRepo.save(obj);
    }
    
    public Cliente update(Cliente obj) {
        Cliente newobj = find(obj.getId());
        updateData(newobj, obj);
        return clienteRepo.save(newobj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possíve excluir: Categoria preenchida!");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return clienteRepo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO) {
	return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);

    }
    
    private void updateData(Cliente newobj, Cliente obj){
        newobj.setNome(obj.getNome());
        newobj.setEmail(obj.getEmail());    
    }
    
}
