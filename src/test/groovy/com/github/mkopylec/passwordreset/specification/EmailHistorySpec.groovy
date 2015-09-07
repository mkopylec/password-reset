package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import com.github.mkopylec.passwordreset.api.PasswordResetEndpoint

import javax.ws.rs.NotFoundException

import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.FULL
import static com.github.mkopylec.passwordreset.assertions.CustomAssertions.assertThat
import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData
import static com.github.mkopylec.passwordreset.utils.DtoFactory.resetDataForMethod

class EmailHistorySpec extends BasicSpec<PasswordResetEndpoint> {

    def "Should get email history when user has changed password before"() {
        given:
        def userData = completeUserData()
        userData.username = 'username1'
        endpoint.saveUser(userData)

        def resetData = resetDataForMethod(userData, FULL)
        endpoint.sendPasswordResetEmail(userData.id, resetData)
        endpoint.sendPasswordResetEmail(userData.id, resetData)

        userData.username = 'username2'
        endpoint.saveUser(userData)

        resetData = resetDataForMethod(userData, FULL)
        endpoint.sendPasswordResetEmail(userData.id, resetData)

        when:
        def history = endpoint.getEmailHistory(userData.id)

        then:
        assertThat(history)
                .containsEntries(3)
                .contains(2).entriesFor('username1', userData.email)
                .and()
                .contains(1).entriesFor('username2', userData.email)
    }

    def "Should not get email history when user does not have history"() {
        given:
        def userData = completeUserData()
        endpoint.saveUser(userData)

        when:
        endpoint.getEmailHistory(userData.id)

        then:
        thrown NotFoundException
    }
}
