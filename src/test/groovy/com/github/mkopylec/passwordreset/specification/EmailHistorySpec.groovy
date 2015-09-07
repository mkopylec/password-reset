package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import com.github.mkopylec.passwordreset.api.PasswordResetEndpoint
import com.github.mkopylec.passwordreset.api.dto.Password

import javax.ws.rs.NotFoundException

import static com.github.mkopylec.passwordreset.assertions.CustomAssertions.assertThat
import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData

class EmailHistorySpec extends BasicSpec<PasswordResetEndpoint> {

    def "Should get email history when user has changed password before"() {
        given:
        def userData1 = completeUserData()
        userData1.username = 'username1'
        userData1.email = 'email1@gmail.com'
        endpoint.saveUser(userData1)

        endpoint.changePassword(userData1.id, new Password(text: 'password1'))
        endpoint.changePassword(userData1.id, new Password(text: 'password2'))

        def userData2 = completeUserData()
        userData2.id = userData1.id
        userData2.username = 'username2'
        userData2.email = 'email2@gmail.com'
        endpoint.changePassword(userData2.id, new Password(text: 'password3'))

        when:
        def history = endpoint.getEmailHistory(userData1.id)

        then:
        assertThat(history)
                .containsEntries(3)
                .contains(2).entriesFor(userData1.username, userData1.email)
                .and()
                .contains(1).entriesFor(userData2.username, userData2.email)
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
