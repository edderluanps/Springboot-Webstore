package com.project.webstore.repositories;

import com.project.webstore.domains.Cliente;
import com.project.webstore.domains.Pedidos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos, Integer> {

	@Transactional(readOnly=true)
	Page<Pedidos> findByCliente(Cliente cliente, Pageable pageRequest);
}