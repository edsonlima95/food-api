package com.edson.foodapi.domain.infra.service;


import com.edson.foodapi.api.core.email.EmailProperties;
import com.edson.foodapi.domain.service.SendEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Service
public class SmtpEmailServiceImpl implements SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void enviar(Mensagem mensagem) {

        try {

            String corpo = this.gerarTemplate(mensagem);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(mensagem.getDestinatarios().toArray(String[]::new));
            helper.setSubject(mensagem.getAssunto());
            helper.setText(corpo, true);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new EmailException("Erro ao tentar enviar o email ", e);
        }

    }

    private String gerarTemplate(Mensagem mensagem) {

        try {
            Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());

        } catch (Exception e) {
            throw new EmailException("Erro ao tentar processar o template", e);
        }
    }
}
