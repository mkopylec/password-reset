package com.github.mkopylec.passwordreset.domain.user;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class User {

    private long id;
    private Credentials credentials;
    private String email;
    private String maidenName;
    private FullName fullName;

    User(long id, Credentials credentials, String email, String maidenName, FullName fullName) {
        checkNotNull(credentials != null, "Credentials cannot be empty");
        checkNotNull(fullName != null, "Full name cannot be empty");
        checkArgument(hasUsernameOrEmail(credentials.getUsername(), email), "Username and e-mail address cannot be empty");
        this.id = id;
        this.credentials = credentials;
        this.email = email;
        this.maidenName = maidenName;
        this.fullName = fullName;
    }

    public void changeUsername(String username) {
        credentials = new Credentials(username, credentials.getHashedPassword());
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
