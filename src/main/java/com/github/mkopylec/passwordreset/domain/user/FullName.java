package com.github.mkopylec.passwordreset.domain.user;

import static org.apache.commons.lang3.StringUtils.isNoneBlank;

class FullName {

    private final String firstName;
    private final String lastName;

    public FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean isFullyNamed() {
        return isNoneBlank(firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
