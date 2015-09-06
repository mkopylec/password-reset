package com.github.mkopylec.passwordreset.application;

import com.github.mkopylec.passwordreset.api.dto.ResetData;
import org.springframework.stereotype.Service;

@Service
class EmailService {

    public void sendPasswordResetEmail(long id, ResetData resetData) {
        throw new UnsupportedOperationException();
    }
}
