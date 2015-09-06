package com.github.mkopylec.passwordreset.domain.user;

import org.springframework.stereotype.Component;

import static com.github.mkopylec.passwordreset.domain.user.UserBuilder.anUser;

@Component
public class UserFactory {

    public UserBuilder createUser(long id, String hashedPassword) {
        return anUser()
                .withId(id)
                .withHashedPassword(hashedPassword);
    }
}
