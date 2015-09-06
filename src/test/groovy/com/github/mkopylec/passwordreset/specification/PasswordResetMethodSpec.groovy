package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import com.github.mkopylec.passwordreset.api.UserEndpoint

import javax.ws.rs.NotFoundException

import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.FULL
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.NOT_AVAILABLE
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.SIMPLE
import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData
import static com.github.mkopylec.passwordreset.utils.DtoFactory.userDataWithoutMaiden
import static com.github.mkopylec.passwordreset.utils.DtoFactory.userDataWithoutMaidenAndName

class PasswordResetMethodSpec extends BasicSpec<UserEndpoint> {

    def "Should get full password reset method when user has maiden name, first and last name"() {
        given:
        def userData = completeUserData()
        getEndpoint().saveUser(userData)

        when:
        def resetMethod = getEndpoint().getPasswordResetMethod(userData.id)

        then:
        resetMethod == FULL
    }

    def "Should get simple password reset method when user has no maiden name but has first and last name"() {
        given:
        def userData = userDataWithoutMaiden()
        getEndpoint().saveUser(userData)

        when:
        def resetMethod = getEndpoint().getPasswordResetMethod(userData.id)

        then:
        resetMethod == SIMPLE
    }

    def "Should not allow to reset password when user has no maiden name, first and last name"() {
        given:
        def userData = userDataWithoutMaidenAndName()
        getEndpoint().saveUser(userData)

        when:
        def resetMethod = getEndpoint().getPasswordResetMethod(userData.id)

        then:
        resetMethod == NOT_AVAILABLE
    }

    def "Should not get password reset method when user does not exist"() {
        when:
        getEndpoint().getPasswordResetMethod(123)

        then:
        thrown NotFoundException
    }
}
