package com.github.mkopylec.passwordreset.domain.user;

public interface UserRepository {

    void save(User user);

    User findById(long id);

    User findByLoginOrEmail(String loginOrEmail);
}
