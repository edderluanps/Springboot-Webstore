package com.project.webstore.services.validator;

import com.project.webstore.domains.Cliente;
import com.project.webstore.dto.ClienteDTO;
import com.project.webstore.repositories.ClienteRepository;
import com.project.webstore.resources.exceptions.FieldMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public void initializable(ClienteUpdate ann){
        
    }
    
    @Override
    public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context){
        
        Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        
        Integer uriId = Integer.parseInt(map.get("id"));
        
        List<FieldMessage> list = new ArrayList<>();
        
        Cliente aux =clienteRepository.findByEmail(objDTO.getEmail());
        
        if(aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "email já existente"));
        }
        
        for (FieldMessage e : list){
            
            context.disableDefaultConstraintViolation();
            
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName());
            
        }
        return list.isEmpty();
    }
    
}
