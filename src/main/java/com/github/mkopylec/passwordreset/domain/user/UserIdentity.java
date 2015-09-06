package com.github.mkopylec.passwordreset.domain.user;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

class UserIdentity {

    private final String username;
    private final String email;

    public UserIdentity(String username, String email) {
        checkArgument(hasUsernameOrEmail(username, email), "User must have username or e-mail address");
        this.username = username;
        this.email = email;
    }

    private boolean hasUsernameOrEmail(String username, String email) {
        return isNotBlank(username) || isNotBlank(email);
    }
}
