package com.github.mkopylec.passwordreset.assertions

import com.github.mkopylec.passwordreset.domain.user.User
import groovy.transform.PackageScope

@PackageScope
class UserAssert {

    private final User actual

    protected UserAssert(User actual) {
        actual != null
        this.actual = actual
    }

    UserAssert hasId(long id) {
        actual.id == id
        return this
    }

    UserAssert hasUsername(String username) {
        actual.username == username
        return this
    }

    UserAssert hasHashedPassword(String hashedPassword) {
        actual.hashedPassword == hashedPassword
        return this
    }

    UserAssert hasEmail(String email) {
        actual.email == email
        return this
    }

    UserAssert hasMaidenName(String maidenName) {
        actual.maidenName == maidenName
        return this
    }

    UserAssert hasFirstName(String firstName) {
        actual.firstName == firstName
        return this
    }

    UserAssert hasLastName(String lastName) {
        actual.lastName == lastName
        return this
    }
}
