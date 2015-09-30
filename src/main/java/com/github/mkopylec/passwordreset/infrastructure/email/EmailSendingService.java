package com.github.mkopylec.passwordreset.infrastructure.email;

import com.github.mkopylec.passwordreset.domain.notification.EmailSender;
import com.github.mkopylec.passwordreset.domain.notification.PasswordResetEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
class EmailSendingService implements EmailSender {

    private final MailSender mailSender;

    @Autowired
    public EmailSendingService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendPasswordResetEmail(PasswordResetEmail email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getRecipientEmail());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getMessage());
        mailSender.send(mailMessage);
    }
}
