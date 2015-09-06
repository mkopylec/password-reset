package com.github.mkopylec.passwordreset.domain.user;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class User {

    private long id;
    private String username;
    private String email;
    private String maidenName;
    private FullName fullName;

    User(long id, String username, String email, String maidenName, FullName fullName) {
        checkArgument(hasUsernameOrEmail(username, email), "Username and e-mail address cannot be empty");
        checkNotNull(fullName != null, "Full name cannot be empty");
        this.id = id;
        this.username = username;
        this.email = email;
        this.maidenName = maidenName;
        this.fullName = fullName;
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changeMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public void rename(String firstName, String lastName) {
        fullName = new FullName(firstName, lastName);
    }

    private boolean hasUsernameOrEmail(String username, String email) {
        return isNotBlank(username) || isNotBlank(email);
    }
}
