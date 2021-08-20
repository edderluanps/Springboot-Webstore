package com.project.webstore.repositories;

import com.project.webstore.domains.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer>{
    
    
    
}
