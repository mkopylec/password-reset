package com.github.mkopylec.passwordreset.domain.user;

public class UserBuilder {

    private long id;
    private String username;
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

    UserBuilder withUsername(String username) {
        this.username = username;
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
        return new User(id, username, email, maidenName, fullName);
    }
}
