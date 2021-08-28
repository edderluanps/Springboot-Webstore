package com.project.webstore.dto;

import com.project.webstore.domains.Categorias;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class CategoriasDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "Tamanho deve estar entre 5 e 80 caracteres")
    private String name;

    public CategoriasDTO() {
    }
    
    public CategoriasDTO(Categorias obj){
        
        id = obj.getId();
        name = obj.getName();
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
