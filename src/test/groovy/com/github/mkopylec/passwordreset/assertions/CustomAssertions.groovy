package com.github.mkopylec.passwordreset.assertions

import com.github.mkopylec.passwordreset.api.dto.ResetEmail
import com.github.mkopylec.passwordreset.domain.user.User

class CustomAssertions {

    static UserAssert assertThat(User actual) {
        return new UserAssert(actual)
    }

    static ResetEmailHistoryAssert assertThat(List<ResetEmail> actual) {
        return new ResetEmailHistoryAssert(actual)
    }
}
