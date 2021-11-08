package com.project.webstore.services;

import com.project.webstore.domains.Pedidos;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    
    void sendOrderConfirmationEmail(Pedidos obj);
    
    void sendEmail(SimpleMailMessage msg);
    
}
