package com.project.webstore;

import com.project.webstore.domains.Categories;
import com.project.webstore.domains.Cidade;
import com.project.webstore.domains.Estado;
import com.project.webstore.domains.Products;
import com.project.webstore.repositories.CategoriesRepository;
import com.project.webstore.repositories.CidadeRepository;
import com.project.webstore.repositories.EstadoRepository;
import com.project.webstore.repositories.ProductsRepository;
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


    }

}
