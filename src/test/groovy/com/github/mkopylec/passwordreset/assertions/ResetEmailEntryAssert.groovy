package com.github.mkopylec.passwordreset.assertions

import com.github.mkopylec.passwordreset.api.dto.ResetEmail
import groovy.transform.PackageScope

@PackageScope
class ResetEmailEntryAssert {

    private int numberOfEntries
    private List<ResetEmail> actual
    private ResetEmailHistoryAssert parentAssert

    protected ResetEmailEntryAssert(int numberOfEntries, List<ResetEmail> actual, ResetEmailHistoryAssert parentAssert) {
        assert actual != null
        this.numberOfEntries = numberOfEntries
        this.actual = actual
        this.parentAssert = parentAssert
    }

    ResetEmailEntryAssert entriesFor(String username, String email) {
        assert numberOfEntries == actual.findAll { it.username == username && it.email == email && it.sendDate != null }.size()
        return this
    }

    ResetEmailHistoryAssert and() {
        return parentAssert
    }
}
