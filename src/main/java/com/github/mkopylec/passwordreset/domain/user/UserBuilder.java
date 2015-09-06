package com.github.mkopylec.passwordreset.domain.user;

public class UserBuilder {

    private long id;
    private String username;
    private String hashedPassword;
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

    UserBuilder withHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withEmail(String email) {
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
        return new User(id, new Credentials(username, hashedPassword), email, maidenName, fullName);
    }
}
