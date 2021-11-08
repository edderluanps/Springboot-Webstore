package com.project.webstore.services;

import com.project.webstore.domains.Pedidos;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailsender;

    @Autowired
    private JavaMailSender jms;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {

        LOG.info("Enviando email...");
        mailsender.send(msg);
        LOG.info("Email enviado! ");

    }

    @Override
    public void SendHtmlEmail(MimeMessage msg) {

        LOG.info("Enviando email...");
        jms.send(msg);
        LOG.info("Email enviado! ");

    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedidos obj) {

        LOG.info("Enviando email...");
        //jms.send(msg);
        LOG.info("Email enviado! ");

    }

}
