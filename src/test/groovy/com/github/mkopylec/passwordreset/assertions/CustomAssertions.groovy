package com.github.mkopylec.passwordreset.assertions

import com.github.mkopylec.passwordreset.domain.user.User

class CustomAssertions {

    static UserAssert assertThat(User actual) {
        return new UserAssert(actual)
    }
}
