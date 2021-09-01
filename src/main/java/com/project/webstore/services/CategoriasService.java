package com.project.webstore.services;

import com.project.webstore.domains.Categorias;
import com.project.webstore.dto.CategoriasDTO;
import com.project.webstore.services.exception.ObjectNotFoundException;
import com.project.webstore.services.exception.DataIntegrityException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.webstore.repositories.CategoriasRepository;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

@Service
public class CategoriasService {
    
    @Autowired
    private CategoriasRepository catrepo;
    
    public List<Categorias> findAll() {
        return catrepo.findAll();
    }
    
    public Categorias find(Integer id) {
        Optional<Categorias> obj = catrepo.findById(id);
        
        return obj.orElseThrow(() -> new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo: " + Categorias.class.getName()));
    }
    
    public Categorias insert(Categorias obj){
        return catrepo.save(obj);
    }
    
    public Categorias update(Categorias obj) {
        Categorias newobj = find(obj.getId());
        updateData(newobj, obj);
        return catrepo.save(newobj);
    }

    public void delete(Integer id){
        find(id);
        try {
            catrepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possíve excluir: Categoria preenchida!");
        }
    }
    
    public Page<Categorias> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return catrepo.findAll(pageRequest);
    }

    public Categorias fromDTO(CategoriasDTO objDTO) {
        return new Categorias(objDTO.getId(), objDTO.getName());

    }

    private void updateData(Categorias newobj, Categorias obj){
        newobj.setName(obj.getName());
  
    }

}
