package com.github.mkopylec.passwordreset.domain.user;

public interface PasswordHasher {

    String hash(String password);
}
