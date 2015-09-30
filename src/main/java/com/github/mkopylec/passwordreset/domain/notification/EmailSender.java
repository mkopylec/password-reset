package com.github.mkopylec.passwordreset.domain.notification;

public interface EmailSender {

    void sendPasswordResetEmail(PasswordResetEmail email);
}
