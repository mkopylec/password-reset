package com.github.mkopylec.passwordreset.api.dto;

import com.github.mkopylec.passwordreset.api.validation.NotEmptyUsernameOrEmail;
import org.hibernate.validator.constraints.NotBlank;

@NotEmptyUsernameOrEmail(message = "Empty username and e-mail address")
public class UserData {

    private long id;
    private String username;
    @NotBlank(message = "Empty hashed password")
    private String hashedPassword;
    private String email;
    private String maidenName;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
