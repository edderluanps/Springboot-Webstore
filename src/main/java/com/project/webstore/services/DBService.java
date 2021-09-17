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
import com.project.webstore.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    //@Autowired
    //private BCryptPasswordEncoder pe;
    @Autowired
    private CategoriasRepository categoriaRepository;

    @Autowired
    private ProdutosRepository produtoRepository;

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

        Produtos p12 = new Produtos(null, "Produto 12", 10.00);
        Produtos p13 = new Produtos(null, "Produto 13", 10.00);
        Produtos p14 = new Produtos(null, "Produto 14", 10.00);
        Produtos p15 = new Produtos(null, "Produto 15", 10.00);
        Produtos p16 = new Produtos(null, "Produto 16", 10.00);
        Produtos p17 = new Produtos(null, "Produto 17", 10.00);
        Produtos p18 = new Produtos(null, "Produto 18", 10.00);
        Produtos p19 = new Produtos(null, "Produto 19", 10.00);
        Produtos p20 = new Produtos(null, "Produto 20", 10.00);
        Produtos p21 = new Produtos(null, "Produto 21", 10.00);
        Produtos p22 = new Produtos(null, "Produto 22", 10.00);
        Produtos p23 = new Produtos(null, "Produto 23", 10.00);
        Produtos p24 = new Produtos(null, "Produto 24", 10.00);
        Produtos p25 = new Produtos(null, "Produto 25", 10.00);
        Produtos p26 = new Produtos(null, "Produto 26", 10.00);
        Produtos p27 = new Produtos(null, "Produto 27", 10.00);
        Produtos p28 = new Produtos(null, "Produto 28", 10.00);
        Produtos p29 = new Produtos(null, "Produto 29", 10.00);
        Produtos p30 = new Produtos(null, "Produto 30", 10.00);
        Produtos p31 = new Produtos(null, "Produto 31", 10.00);
        Produtos p32 = new Produtos(null, "Produto 32", 10.00);
        Produtos p33 = new Produtos(null, "Produto 33", 10.00);
        Produtos p34 = new Produtos(null, "Produto 34", 10.00);
        Produtos p35 = new Produtos(null, "Produto 35", 10.00);
        Produtos p36 = new Produtos(null, "Produto 36", 10.00);
        Produtos p37 = new Produtos(null, "Produto 37", 10.00);
        Produtos p38 = new Produtos(null, "Produto 38", 10.00);
        Produtos p39 = new Produtos(null, "Produto 39", 10.00);
        Produtos p40 = new Produtos(null, "Produto 40", 10.00);
        Produtos p41 = new Produtos(null, "Produto 41", 10.00);
        Produtos p42 = new Produtos(null, "Produto 42", 10.00);
        Produtos p43 = new Produtos(null, "Produto 43", 10.00);
        Produtos p44 = new Produtos(null, "Produto 44", 10.00);
        Produtos p45 = new Produtos(null, "Produto 45", 10.00);
        Produtos p46 = new Produtos(null, "Produto 46", 10.00);
        Produtos p47 = new Produtos(null, "Produto 47", 10.00);
        Produtos p48 = new Produtos(null, "Produto 48", 10.00);
        Produtos p49 = new Produtos(null, "Produto 49", 10.00);
        Produtos p50 = new Produtos(null, "Produto 50", 10.00);

        cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        p12.getCategorias().add(cat1);
        p13.getCategorias().add(cat1);
        p14.getCategorias().add(cat1);
        p15.getCategorias().add(cat1);
        p16.getCategorias().add(cat1);
        p17.getCategorias().add(cat1);
        p18.getCategorias().add(cat1);
        p19.getCategorias().add(cat1);
        p20.getCategorias().add(cat1);
        p21.getCategorias().add(cat1);
        p22.getCategorias().add(cat1);
        p23.getCategorias().add(cat1);
        p24.getCategorias().add(cat1);
        p25.getCategorias().add(cat1);
        p26.getCategorias().add(cat1);
        p27.getCategorias().add(cat1);
        p28.getCategorias().add(cat1);
        p29.getCategorias().add(cat1);
        p30.getCategorias().add(cat1);
        p31.getCategorias().add(cat1);
        p32.getCategorias().add(cat1);
        p33.getCategorias().add(cat1);
        p34.getCategorias().add(cat1);
        p35.getCategorias().add(cat1);
        p36.getCategorias().add(cat1);
        p37.getCategorias().add(cat1);
        p38.getCategorias().add(cat1);
        p39.getCategorias().add(cat1);
        p40.getCategorias().add(cat1);
        p41.getCategorias().add(cat1);
        p42.getCategorias().add(cat1);
        p43.getCategorias().add(cat1);
        p44.getCategorias().add(cat1);
        p45.getCategorias().add(cat1);
        p46.getCategorias().add(cat1);
        p47.getCategorias().add(cat1);
        p48.getCategorias().add(cat1);
        p49.getCategorias().add(cat1);
        p50.getCategorias().add(cat1);

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
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "nelio.cursos@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Cliente cli2 = new Cliente(null, "Ana Costa", "nelio.iftm@gmail.com", "31628382740", TipoCliente.PESSOAFISICA);
        cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
        //cli2.addPerfil(Perfil.ADMIN);

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
        Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);

        cli1.getEndereco().addAll(Arrays.asList(e1, e2));
        cli2.getEndereco().addAll(Arrays.asList(e3));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedidos ped1 = new Pedidos(null, sdf.parse("30/09/2021 10:32"), cli1, e1);
        Pedidos ped2 = new Pedidos(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
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
