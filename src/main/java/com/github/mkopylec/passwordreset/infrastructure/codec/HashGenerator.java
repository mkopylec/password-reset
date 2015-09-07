package com.github.mkopylec.passwordreset.infrastructure.codec;

import com.github.mkopylec.passwordreset.application.user.PasswordHasher;
import org.springframework.stereotype.Service;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@Service
class HashGenerator implements PasswordHasher {

    @Override
    public String hash(String password) {
        return sha256Hex(md5Hex(password));
    }
}
