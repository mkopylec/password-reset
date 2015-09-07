package com.github.mkopylec.passwordreset.application;

import com.github.mkopylec.passwordreset.api.PasswordResetEndpoint;
import com.github.mkopylec.passwordreset.api.dto.Password;
import com.github.mkopylec.passwordreset.api.dto.ResetData;
import com.github.mkopylec.passwordreset.api.dto.ResetEmail;
import com.github.mkopylec.passwordreset.api.dto.ResetMethod;
import com.github.mkopylec.passwordreset.api.dto.UserData;
import com.github.mkopylec.passwordreset.application.email.EmailService;
import com.github.mkopylec.passwordreset.application.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.ok;

@Service
public class PasswordResetService implements PasswordResetEndpoint {

    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public PasswordResetService(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @Override
    public Response saveUser(UserData userData) {
        userService.saveUser(userData);
        return ok().build();
    }

    @Override
    public long getUserId(String loginOrEmail) {
        return userService.getUserId(loginOrEmail);
    }

    @Override
    public ResetMethod getPasswordResetMethod(long id) {
        return userService.getPasswordResetMethod(id);
    }

    @Override
    public Response sendPasswordResetEmail(long id, ResetData resetData) {
        emailService.sendPasswordResetEmail(id, resetData);
        return ok().build();
    }

    @Override
    public Response changePassword(long id, Password password) {
        userService.changePassword(id, password);
        return ok().build();
    }

    @Override
    public List<ResetEmail> getEmailHistory(long id) {
        return emailService.getEmailHistory(id);
    }
}
