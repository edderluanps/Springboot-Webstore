package com.project.webstore;

import com.project.webstore.domains.Categories;
import com.project.webstore.repositories.CategoriesRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootWebstoreApplication implements CommandLineRunner {
    
    @Autowired
    private CategoriesRepository categoriesRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categories cat1 = new Categories(null, "Informática");
        Categories cat2 = new Categories(null, "Escrtitório");

        categoriesRepository.saveAll(Arrays.asList(cat1, cat2));

    }

}
