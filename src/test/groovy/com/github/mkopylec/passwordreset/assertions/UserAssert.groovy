package com.github.mkopylec.passwordreset.assertions

import com.github.mkopylec.passwordreset.domain.user.User
import groovy.transform.PackageScope

@PackageScope
class UserAssert {

    private final User actual

    protected UserAssert(User actual) {
        assert actual != null
        this.actual = actual
    }

    UserAssert hasId(long id) {
        assert actual.id == id
        return this
    }

    UserAssert hasUsername(String username) {
        assert actual.username == username
        return this
    }

    UserAssert hasHashedPassword(String hashedPassword) {
        assert actual.hashedPassword == hashedPassword
        return this
    }

    UserAssert hasEmail(String email) {
        assert actual.email == email
        return this
    }

    UserAssert hasMaidenName(String maidenName) {
        assert actual.maidenName == maidenName
        return this
    }

    UserAssert hasFirstName(String firstName) {
        assert actual.firstName == firstName
        return this
    }

    UserAssert hasLastName(String lastName) {
        assert actual.lastName == lastName
        return this
    }
}
