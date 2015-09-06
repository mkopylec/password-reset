package com.github.mkopylec.passwordreset.domain.user;

class UserBuilder {

    private String username;
    private String email;
    private String maidenName;
    private String firstName;
    private String lastName;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
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

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public User build() {
        return new User(username, email, maidenName, firstName, lastName);
    }
}
