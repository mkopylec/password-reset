package com.github.mkopylec.passwordreset.domain.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Document(collection = "user")
public class User {

    @Id
    private long id;
    private Credentials credentials;
    private String email;
    private String maidenName;
    private FullName fullName;

    User(long id, Credentials credentials, String email, String maidenName, FullName fullName) {
        checkNotNull(credentials, "Credentials cannot be empty");
        checkNotNull(fullName, "Full name cannot be empty");
        checkArgument(hasUsernameOrEmail(credentials.getUsername(), email), "Username and e-mail address cannot be empty");
        this.id = id;
        this.credentials = credentials;
        this.email = email;
        this.maidenName = maidenName;
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return credentials.getUsername();
    }

    public String getHashedPassword() {
        return credentials.getHashedPassword();
    }

    public String getEmail() {
        return email;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public String getFirstName() {
        return fullName.getFirstName();
    }

    public String getLastName() {
        return fullName.getLastName();
    }

    private boolean hasUsernameOrEmail(String username, String email) {
        return isNotBlank(username) || isNotBlank(email);
    }
}
