package com.github.mkopylec.passwordreset.domain.user;

public class User {

    private UserIdentity identity;
    private String maidenName;
    private String firstName;
    private String lastName;

    User(String username, String email, String maidenName, String firstName, String lastName) {
        identity = new UserIdentity(username, email);
        this.maidenName = maidenName;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
