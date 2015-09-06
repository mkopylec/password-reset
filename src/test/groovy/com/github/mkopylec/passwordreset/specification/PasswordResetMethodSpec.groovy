package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec

import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.FULL
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.NOT_AVAILABLE
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.SIMPLE
import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData
import static com.github.mkopylec.passwordreset.utils.DtoFactory.userDataWithoutMaiden
import static com.github.mkopylec.passwordreset.utils.DtoFactory.userDataWithoutMaidenAndName

class PasswordResetMethodSpec extends BasicSpec {

    def "Should get full password reset method when user has maiden name, first and last name"() {
        given:
        def userData = completeUserData()
        getEndpoint().saveUser(userData)

        when:
        def resetMethod = getEndpoint().getPasswordResetMethod(userData.username)

        then:
        resetMethod == FULL

        when:
        resetMethod = getEndpoint().getPasswordResetMethod(userData.email)

        then:
        resetMethod == FULL
    }

    def "Should get simple password reset method when user has no maiden name but has first and last name"() {
        given:
        def userData = userDataWithoutMaiden()
        getEndpoint().saveUser(userData)

        when:
        def resetMethod = getEndpoint().getPasswordResetMethod(userData.username)

        then:
        resetMethod == SIMPLE

        when:
        resetMethod = getEndpoint().getPasswordResetMethod(userData.email)

        then:
        resetMethod == SIMPLE
    }

    def "Should not allow to reset password when user has no maiden name, first and last name"() {
        given:
        def userData = userDataWithoutMaidenAndName()
        getEndpoint().saveUser(userData)

        when:
        def resetMethod = getEndpoint().getPasswordResetMethod(userData.username)

        then:
        resetMethod == NOT_AVAILABLE

        when:
        resetMethod = getEndpoint().getPasswordResetMethod(userData.email)

        then:
        resetMethod == NOT_AVAILABLE
    }
}
