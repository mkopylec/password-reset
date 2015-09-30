package com.github.mkopylec.passwordreset.domain.notification;

import com.github.mkopylec.passwordreset.domain.user.User;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class PasswordResetEmailCreator {

    public PasswordResetEmail createPasswordResetEmail(User user, String passwordResetUrl) {
        String subject = format("Witaj %s %s!", user.getFirstName(), user.getLastName());
        String message = format("Aby dokonczyc proces odnawiania hasla kliknij w link: '%s'", passwordResetUrl);

        return new PasswordResetEmail(user.getEmail(), subject, message);
    }
}
