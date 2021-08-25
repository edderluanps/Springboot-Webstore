package com.project.webstore;

import com.project.webstore.domains.Categories;
import com.project.webstore.domains.Cidade;
import com.project.webstore.domains.Cliente;
import com.project.webstore.domains.Endereco;
import com.project.webstore.domains.Estado;
import com.project.webstore.domains.ItemPedido;
import com.project.webstore.domains.Pagamento;
import com.project.webstore.domains.PagamentoComBoleto;
import com.project.webstore.domains.PagamentoComCartao;
import com.project.webstore.domains.Pedidos;
import com.project.webstore.domains.Products;
import com.project.webstore.domains.enums.EstadoPagamento;
import com.project.webstore.domains.enums.TipoCliente;
import com.project.webstore.repositories.CategoriesRepository;
import com.project.webstore.repositories.CidadeRepository;
import com.project.webstore.repositories.ClienteRepository;
import com.project.webstore.repositories.EnderecoRepository;
import com.project.webstore.repositories.EstadoRepository;
import com.project.webstore.repositories.ItemPedidoRepository;
import com.project.webstore.repositories.PagamentoRepository;
import com.project.webstore.repositories.PedidoRepository;
import com.project.webstore.repositories.ProductsRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootWebstoreApplication implements CommandLineRunner {
    
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ProductsRepository productsRepository;  
    
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
    
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categories cat1 = new Categories(null, "Informática");
        Categories cat2 = new Categories(null, "Escrtitório");
        
        Products prod1 = new Products(null, "Computador", 2000.00);
        Products prod2 = new Products(null, "Impressora", 800.00);
        Products prod3 = new Products(null, "Mouse", 80.00);
        
        cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));
        cat2.getProducts().addAll(Arrays.asList(prod2));
        
        prod1.getCategories().addAll(Arrays.asList(cat1));
        prod2.getCategories().addAll(Arrays.asList(cat1, cat2));
        prod3.getCategories().addAll(Arrays.asList(cat1));

        categoriesRepository.saveAll(Arrays.asList(cat1, cat2));
        productsRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "Sao Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "Sao Paulo C", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);
        
        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));
        
        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
        
        Cliente cli1 = new Cliente(null, "Maria Silva","maria@email.com", "1234567890", TipoCliente.PESSOAFISICA);
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
        
        ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);
        
        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
                
        prod1.getItens().addAll(Arrays.asList(ip1));
        prod2.getItens().addAll(Arrays.asList(ip3));
        prod3.getItens().addAll(Arrays.asList(ip2));    
        
        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
        
    }

}
