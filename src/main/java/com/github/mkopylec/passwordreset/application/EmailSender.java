package com.github.mkopylec.passwordreset.application;

import com.github.mkopylec.passwordreset.domain.user.User;

public interface EmailSender {

    void sendPasswordResetEmail(User user, String resetUrl);
}
