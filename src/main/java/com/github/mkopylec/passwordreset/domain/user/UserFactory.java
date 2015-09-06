package com.github.mkopylec.passwordreset.domain.user;

import org.springframework.stereotype.Component;

import static com.github.mkopylec.passwordreset.domain.user.UserBuilder.anUser;

@Component
public class UserFactory {

    public UserBuilder createUser(long id, String username, String email) {
        return anUser()
                .withId(id)
                .withUsername(username)
                .withEmail(email);
    }
}
