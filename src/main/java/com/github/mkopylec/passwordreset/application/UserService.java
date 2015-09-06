package com.github.mkopylec.passwordreset.application;

import com.github.mkopylec.passwordreset.api.UserEndpoint;
import com.github.mkopylec.passwordreset.api.dto.Password;
import com.github.mkopylec.passwordreset.api.dto.ResetData;
import com.github.mkopylec.passwordreset.api.dto.ResetMethod;
import com.github.mkopylec.passwordreset.api.dto.UserData;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service
public class UserService implements UserEndpoint {

    @Override
    public Response saveUser(UserData userData) {
        return null;
    }

    @Override
    public long getUserId(String loginOrEmail) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ResetMethod getPasswordResetMethod(long id) {
        throw new UnsupportedOperationException();
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
