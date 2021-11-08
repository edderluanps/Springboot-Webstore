package com.project.webstore.services;

import com.project.webstore.domains.Pedidos;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstractEmailService implements EmailService {
    
    @Value("${default.sender}")
    private String sender;
    
    @Autowired
    private TemplateEngine templateengine;
    
    @Autowired
    private JavaMailSender jms;
    
    @Override
    public void sendOrderConfirmationEmail(Pedidos obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);
    }
    
    private SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedidos obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Cód: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }
    
    protected String htmlFromTemplatePedido(Pedidos obj) {
        Context context = new Context();
        context.setVariable("pedido", obj);
        return templateengine.process("email/confirmacaoPedido", context);
        
    }

    //@Override
    public void sendOrderConfirmationHtmlEamil(Pedidos obj) {
        
        try {
            MimeMessage mm = prepareMimeMessageFromPedido(obj);
            SendHtmlEmail(mm);
            
        } catch (MessagingException msgex) {
            sendOrderConfirmationEmail(obj);
        }
    }
    
    protected MimeMessage prepareMimeMessageFromPedido(Pedidos obj) throws MessagingException {
        MimeMessage mimeMsg = jms.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMsg, true);
        mmh.setTo(obj.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido confirmado! Cód: " + obj.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(obj), true);
        return null;
    }
    
}
