package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec

import static com.github.mkopylec.passwordreset.DtoFactory.completeUserData
import static com.github.mkopylec.passwordreset.DtoFactory.userDataWithoutMaiden
import static com.github.mkopylec.passwordreset.DtoFactory.userDataWithoutMaidenAndName

class PasswordResetMethodSpec extends BasicSpec {

    def "Should get full password reset method when user has maiden name, first and last name"() {
        given:
        def userData = completeUserData()
        getEndpoint().saveUser(userData)

        when:
        def resetMethod = getEndpoint().getPasswordResetMethod(userData.username)

        then:
        resetMethod.method == 'FULL'

        when:
        resetMethod = getEndpoint().getPasswordResetMethod(userData.email)

        then:
        resetMethod.method == 'FULL'
    }

    def "Should get simple password reset method when user has no maiden name but has first and last name"() {
        given:
        def userData = userDataWithoutMaiden()
        getEndpoint().saveUser(userData)

        when:
        def resetMethod = getEndpoint().getPasswordResetMethod(userData.username)

        then:
        resetMethod.method == 'SIMPLE'

        when:
        resetMethod = getEndpoint().getPasswordResetMethod(userData.email)

        then:
        resetMethod.method == 'SIMPLE'
    }

    def "Should not allow to reset password when user has no maiden name, first and last name"() {
        given:
        def userData = userDataWithoutMaidenAndName()
        getEndpoint().saveUser(userData)

        when:
        def resetMethod = getEndpoint().getPasswordResetMethod(userData.username)

        then:
        resetMethod.method == 'NOT_AVAILABLE'

        when:
        resetMethod = getEndpoint().getPasswordResetMethod(userData.email)

        then:
        resetMethod.method == 'NOT_AVAILABLE'
    }
}
