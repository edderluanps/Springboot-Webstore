package com.project.webstore.services;

import com.project.webstore.domains.Cidade;
import com.project.webstore.domains.Cliente;
import com.project.webstore.domains.Endereco;
import com.project.webstore.domains.enums.TipoCliente;
import com.project.webstore.dto.ClienteDTO;
import com.project.webstore.dto.ClienteNewDTO;
import com.project.webstore.repositories.CidadeRepository;
import com.project.webstore.repositories.ClienteRepository;
import com.project.webstore.repositories.EnderecoRepository;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;
    
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;    
    
    public List<Cliente> findAll() {
        return clienteRepo.findAll();
    }
    
    public Cliente find(Integer id) {
        Optional<Cliente> obj = clienteRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
    
    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepo.save(obj);
        enderecoRepository.saveAll(obj.getEndereco());
        return obj;
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

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEndereco().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }
    
    private void updateData(Cliente newobj, Cliente obj){
        newobj.setNome(obj.getNome());
        newobj.setEmail(obj.getEmail());    
    }
    
}
