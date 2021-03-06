package com.project.webstore.services.validator;

import com.project.webstore.domains.Cliente;
import com.project.webstore.domains.enums.TipoCliente;
import com.project.webstore.dto.ClienteNewDTO;
import com.project.webstore.repositories.ClienteRepository;
import com.project.webstore.resources.exceptions.FieldMessage;
import com.project.webstore.services.validator.util.BR;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public void initializable(ClienteInsert ann){
        
    }
    
    @Override
    public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context){
        
        List<FieldMessage> list = new ArrayList<>();
        
        if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && BR.isValidCPF(objDTO.getCpfOuCnpj()))
            list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));

        if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && BR.isValidCNPJ(objDTO.getCpfOuCnpj()))
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
        
        Cliente aux =clienteRepository.findByEmail(objDTO.getEmail());
        
        if(aux != null){
            list.add(new FieldMessage("email", "email já existente"));
        }
        
        for (FieldMessage e : list){
            
            context.disableDefaultConstraintViolation();
            
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName());
            
        }
        return list.isEmpty();
    }
    
}
