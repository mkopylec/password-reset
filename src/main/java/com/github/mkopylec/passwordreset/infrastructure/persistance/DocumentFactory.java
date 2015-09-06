package com.github.mkopylec.passwordreset.infrastructure.persistance;

import com.github.mkopylec.passwordreset.domain.user.User;
import org.springframework.stereotype.Component;

@Component
class DocumentFactory {

    public UserLoginDocument createUserLoginDocument(User user) {
        UserLoginDocument document = new UserLoginDocument();
        document.setUsername(user.getUsername());
        document.setId(user.getId());
        return document;
    }

    public UserEmailDocument createUserEmailDocument(User user) {
        UserEmailDocument document = new UserEmailDocument();
        document.setEmail(user.getEmail());
        document.setId(user.getId());
        return document;
    }
}
