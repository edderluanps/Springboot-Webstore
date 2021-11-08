package com.project.webstore.services;

import com.project.webstore.domains.Pedidos;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedidos obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedidos obj);

    void SendHtmlEmail(MimeMessage msg);

}
