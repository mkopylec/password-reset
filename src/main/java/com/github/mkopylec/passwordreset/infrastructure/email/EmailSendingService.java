package com.github.mkopylec.passwordreset.infrastructure.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
class EmailSendingService {

    private final MailSender mailSender;

    @Autowired
    public EmailSendingService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    //TODO Doda? potrzebne metody do wysy?ania e-maili

    private void sendEmail(String recipient, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
