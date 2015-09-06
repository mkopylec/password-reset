package com.github.mkopylec.passwordreset.application;

import com.github.mkopylec.passwordreset.api.dto.ResetData;
import com.github.mkopylec.passwordreset.domain.user.User;
import com.github.mkopylec.passwordreset.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.mkopylec.passwordreset.application.check.Preconditions.notFoundIfNull;

@Service
class EmailService {

    private final UserRepository userRepository;
    private final EmailSender emailSender;

    @Autowired
    public EmailService(UserRepository userRepository, EmailSender emailSender) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
    }

    public void sendPasswordResetEmail(long id, ResetData resetData) {
        User user = userRepository.findById(id);
        notFoundIfNull(user, "User with id: '" + id + "' does not exist");
        emailSender.sendPasswordResetEmail(user, resetData.getResetUrl());
    }
}
