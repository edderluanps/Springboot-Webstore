package com.project.webstore.services.validator;

import com.project.webstore.domains.enums.TipoCliente;
import com.project.webstore.dto.ClienteNewDTO;
import com.project.webstore.resources.exceptions.FieldMessage;
import com.project.webstore.services.validator.util.BR;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{

    public void initializable(ClienteInsert ann){
        
    }
    
    @Override
    public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context){
        
        List<FieldMessage> list = new ArrayList<>();
        
        if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && BR.isValidCPF(objDTO.getCpfouCnpj()))
            list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));

        if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && BR.isValidCNPJ(objDTO.getCpfouCnpj()))
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
        
        for (FieldMessage e : list){
            
            context.disableDefaultConstraintViolation();
            
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName());
            
        }
        return list.isEmpty();
    }
    
}
