package com.project.webstore.services;

import com.project.webstore.domains.Categorias;
import com.project.webstore.domains.Produtos;
import com.project.webstore.repositories.CategoriasRepository;
import com.project.webstore.services.exception.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.webstore.repositories.ProdutosRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtoRepo;

    @Autowired
    CategoriasRepository catRepo;

    public Produtos find(Integer id) {
        Optional<Produtos> obj = produtoRepo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo: " + Produtos.class.getName()));
    }

    public Page<Produtos> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        List<Categorias> categorias = catRepo.findAllById(ids);
        return produtoRepo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
