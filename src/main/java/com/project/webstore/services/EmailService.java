package com.project.webstore.services;

import com.project.webstore.domains.Cliente;
import org.springframework.mail.SimpleMailMessage;
import com.project.webstore.domains.Pedidos;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedidos obj);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
