package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec

import javax.ws.rs.NotFoundException

import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData

class UserIdSpec extends BasicSpec {

    def "Should get user id by login or e-mail address"() {
        given:
        def userData = completeUserData()
        getEndpoint().saveUser(userData)

        when:
        def userId = getEndpoint().getUserId(userData.username)

        then:
        userId == userData.id

        when:
        userId = getEndpoint().getUserId(userData.email)

        then:
        userId == userData.id
    }

    def "Should not get user id by login or e-mail address when user does not exist"() {
        when:
        getEndpoint().getUserId('non_existing_login_or_email')

        then:
        thrown NotFoundException
    }
}
