package com.github.mkopylec.passwordreset.domain.user;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

class Credentials {

    private final String username;
    private final String hashedPassword;

    public Credentials(String username, String hashedPassword) {
        checkArgument(isNotBlank(hashedPassword), "Hashed password cannot be empty");
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
