package com.project.webstore.services;

import com.project.webstore.domains.Categorias;
import com.project.webstore.domains.Cidade;
import com.project.webstore.domains.Cliente;
import com.project.webstore.domains.Endereco;
import com.project.webstore.domains.Estado;
import com.project.webstore.domains.ItemPedido;
import com.project.webstore.domains.Pagamento;
import com.project.webstore.domains.PagamentoComBoleto;
import com.project.webstore.domains.PagamentoComCartao;
import com.project.webstore.domains.Pedidos;
import com.project.webstore.domains.Produtos;
import com.project.webstore.domains.enums.EstadoPagamento;
import com.project.webstore.domains.enums.TipoCliente;
import com.project.webstore.repositories.CategoriasRepository;
import com.project.webstore.repositories.CidadeRepository;
import com.project.webstore.repositories.ClienteRepository;
import com.project.webstore.repositories.EnderecoRepository;
import com.project.webstore.repositories.EstadoRepository;
import com.project.webstore.repositories.ItemPedidoRepository;
import com.project.webstore.repositories.PagamentoRepository;
import com.project.webstore.repositories.PedidoRepository;
import com.project.webstore.repositories.ProdutosRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {
    
    @Autowired
    private BCryptPasswordEncoder bcpe;

    @Autowired
    private ProdutosRepository productsRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private CategoriasRepository categoriaRepository;

    public void instantiateTestDatabase() throws ParseException {

        Categorias cat1 = new Categorias(null, "Informática");
        Categorias cat2 = new Categorias(null, "Escritório");
        Categorias cat3 = new Categorias(null, "Cama mesa e banho");
        Categorias cat4 = new Categorias(null, "Eletrônicos");
        Categorias cat5 = new Categorias(null, "Jardinagem");
        Categorias cat6 = new Categorias(null, "Decoração");
        Categorias cat7 = new Categorias(null, "Perfumaria");

        Produtos p1 = new Produtos(null, "Computador", 2000.00);
        Produtos p2 = new Produtos(null, "Impressora", 800.00);
        Produtos p3 = new Produtos(null, "Mouse", 80.00);
        Produtos p4 = new Produtos(null, "Mesa de escritório", 300.00);
        Produtos p5 = new Produtos(null, "Toalha", 50.00);
        Produtos p6 = new Produtos(null, "Colcha", 200.00);
        Produtos p7 = new Produtos(null, "TV true color", 1200.00);
        Produtos p8 = new Produtos(null, "Roçadeira", 800.00);
        Produtos p9 = new Produtos(null, "Abajour", 100.00);
        Produtos p10 = new Produtos(null, "Pendente", 180.00);
        Produtos p11 = new Produtos(null, "Shampoo", 90.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productsRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "Sao Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "Sao Paulo C", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "mariasilva@email.com", "1234567890", TipoCliente.PESSOAFISICA, bcpe.encode("12345"));

        cli1.getTelefones().addAll(Arrays.asList("87654321", "12345678"));

        Endereco e1 = new Endereco(null, "Rua X", "1", "prox. ao castelo do rei", "centro", "33987-000", cli1, c1);
        Endereco e2 = new Endereco(null, "Rua Y", "2", "prox. ao castelo da rainha", "centro", "12345-000", cli1, c2);

        cli1.getEndereco().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedidos ped1 = new Pedidos(null, sdf.parse("30/09/2021 10:50"), cli1, e1);
        Pedidos ped2 = new Pedidos(null, sdf.parse("30/09/2021 09:58"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2021 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

    }
}
