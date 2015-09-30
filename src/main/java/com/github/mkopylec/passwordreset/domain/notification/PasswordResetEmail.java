package com.github.mkopylec.passwordreset.domain.notification;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class PasswordResetEmail {

    private final String recipientEmail;
    private final String subject;
    private final String message;

    PasswordResetEmail(String recipientEmail, String subject, String message) {
        checkArgument(isNotBlank(recipientEmail), "Recipient e-mail address cannot be empty");
        checkArgument(isNotBlank(subject), "E-mail subject cannot be empty");
        checkArgument(isNotBlank(message), "E-mail message cannot be empty");
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
