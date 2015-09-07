package com.github.mkopylec.passwordreset.assertions

import com.github.mkopylec.passwordreset.api.dto.ResetEmail
import groovy.transform.PackageScope

@PackageScope
class ResetEmailHistoryAssert {

    private List<ResetEmail> actual

    protected ResetEmailHistoryAssert(List<ResetEmail> actual) {
        assert actual != null
        this.actual = actual
    }

    ResetEmailEntryAssert contains(int number) {
        return new ResetEmailEntryAssert(number, actual, this)
    }
}
