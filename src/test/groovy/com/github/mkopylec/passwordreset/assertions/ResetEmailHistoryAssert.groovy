package com.github.mkopylec.passwordreset.assertions

import com.github.mkopylec.passwordreset.api.dto.ResetEmail
import groovy.transform.PackageScope

@PackageScope
class ResetEmailHistoryAssert {

    private final List<ResetEmail> actual

    protected ResetEmailHistoryAssert(List<ResetEmail> actual) {
        assert actual != null
        this.actual = actual
    }

    ResetEmailHistoryAssert hasNumberOfEntries(int number) {
        assert actual.size() == number
        return this
    }
}
