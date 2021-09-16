package com.project.webstore.repositories;

import com.project.webstore.domains.Categorias;
import com.project.webstore.domains.Produtos;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT obj FROM Produtos obj INNER JOIN obj.categorias cat WHERE obj.name LIKE %:name% AND cat IN :categorias")
    Page<Produtos> findDistinctByNomeContainingAndCategoriasIn(@Param("name") String name, @Param("categorias") List<Categorias> categorias, Pageable pageRequest);

}
