package com.wimp.global.service;

import com.wimp.global.dto.SendMailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void send(SendMailDto sendMailDto) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(sendMailDto.getTo());
        helper.setFrom(sendMailDto.getFrom());
        helper.setSubject(sendMailDto.getSubject());
        helper.setText(sendMailDto.getText(), true);
        mailSender.send(message);
    }
}
