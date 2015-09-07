package com.github.mkopylec.passwordreset.application.user;

public interface PasswordHasher {

    String hash(String password);
}
