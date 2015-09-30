package com.github.mkopylec.passwordreset.domain.notification;

public class PasswordResetEmail {

    private final String recipientEmail;
    private final String subject;
    private final String message;

    PasswordResetEmail(String recipientEmail, String subject, String message) {
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.message = message;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
