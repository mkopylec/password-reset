package com.github.mkopylec.passwordreset.application;

import com.github.mkopylec.passwordreset.api.dto.Password;
import com.github.mkopylec.passwordreset.api.dto.ResetData;
import com.github.mkopylec.passwordreset.api.dto.ResetMethod;
import com.github.mkopylec.passwordreset.api.dto.UserData;
import com.github.mkopylec.passwordreset.api.UserEndpoint;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service
public class UserService implements UserEndpoint {

    @Override
    public Response saveUser(UserData userData) {
        return null;
    }

    @Override
    public ResetMethod getPasswordResetMethod(String loginOrEmail) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response sendPasswordResetEmail(String loginOrEmail, ResetData resetData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response changePassword(String loginOrEmail, Password password) {
        throw new UnsupportedOperationException();
    }
}
