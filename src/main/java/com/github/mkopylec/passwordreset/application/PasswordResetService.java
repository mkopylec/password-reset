package com.github.mkopylec.passwordreset.application;

import com.github.mkopylec.passwordreset.api.PasswordResetEndpoint;
import com.github.mkopylec.passwordreset.api.dto.Password;
import com.github.mkopylec.passwordreset.api.dto.ResetData;
import com.github.mkopylec.passwordreset.api.dto.ResetMethod;
import com.github.mkopylec.passwordreset.api.dto.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Service
public class PasswordResetService implements PasswordResetEndpoint {

    private final UserService userService;

    @Autowired
    public PasswordResetService(UserService userService) {
        this.userService = userService;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Response changePassword(long id, Password password) {
        throw new UnsupportedOperationException();
    }
}
