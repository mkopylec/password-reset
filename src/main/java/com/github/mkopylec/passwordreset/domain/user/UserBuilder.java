package com.github.mkopylec.passwordreset.domain.user;

public class UserBuilder {

    private long id;
    private Credentials credentials;
    private String email;
    private String maidenName;
    private FullName fullName;

    private UserBuilder() {
    }

    static UserBuilder anUser() {
        return new UserBuilder();
    }

    UserBuilder withId(long id) {
        this.id = id;
        return this;
    }

    UserBuilder withCredentials(String username, String hashedPassword) {
        credentials = new Credentials(username, hashedPassword);
        return this;
    }

    UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withMaidenName(String maidenName) {
        this.maidenName = maidenName;
        return this;
    }

    public UserBuilder withFullName(String firstName, String lastName) {
        fullName = new FullName(firstName, lastName);
        return this;
    }

    public User create() {
        return new User(id, credentials, email, maidenName, fullName);
    }
}
