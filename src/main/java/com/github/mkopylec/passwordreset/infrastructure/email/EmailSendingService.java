package com.github.mkopylec.passwordreset.infrastructure.email;

import com.github.mkopylec.passwordreset.application.EmailSender;
import com.github.mkopylec.passwordreset.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
class EmailSendingService implements EmailSender {

    private final MailSender mailSender;

    @Autowired
    public EmailSendingService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendPasswordResetEmail(User user, String resetUrl) {
        String subject = format("Witaj %s %s!", user.getFirstName(), user.getLastName());
        String message = format("Aby dokonczyc proces odnawiania hasla kliknij w link: '%s'", resetUrl);
        sendEmail(user.getEmail(), subject, message);
    }

    private void sendEmail(String recipient, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
