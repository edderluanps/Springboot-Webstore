package com.project.webstore.services;

import com.project.webstore.domains.Cliente;
import com.project.webstore.domains.ItemPedido;
import com.project.webstore.domains.PagamentoComBoleto;
import com.project.webstore.domains.Pedidos;
import com.project.webstore.domains.enums.EstadoPagamento;
import com.project.webstore.repositories.ItemPedidoRepository;
import com.project.webstore.repositories.PagamentoRepository;
import com.project.webstore.repositories.PedidoRepository;
import com.project.webstore.security.UserSS;
import com.project.webstore.services.exception.AuthorizationException;
import com.project.webstore.services.exception.ObjectNotFoundException;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutosService produtoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public Pedidos find(Integer id) {
        Optional<Pedidos> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedidos.class.getName()));
    }

    public Pedidos insert(Pedidos obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPrice());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationEmail(obj);
        return obj;
    }

    public Page<Pedidos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.find(user.getId());
        return repo.findByCliente(cliente, pageRequest);
    }
}
