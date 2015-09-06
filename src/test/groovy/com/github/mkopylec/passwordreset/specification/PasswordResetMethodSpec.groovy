package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import com.github.mkopylec.passwordreset.api.PasswordResetEndpoint

import javax.ws.rs.NotFoundException

import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.FULL
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.NOT_AVAILABLE
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.SIMPLE
import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData
import static com.github.mkopylec.passwordreset.utils.DtoFactory.userDataWithoutMaiden
import static com.github.mkopylec.passwordreset.utils.DtoFactory.userDataWithoutMaidenAndName

class PasswordResetMethodSpec extends BasicSpec<PasswordResetEndpoint> {

    def "Should get full password reset method when user has maiden name, first and last name"() {
        given:
        def userData = completeUserData()
        endpoint.saveUser(userData)

        when:
        def resetMethod = endpoint.getPasswordResetMethod(userData.id)

        then:
        resetMethod == FULL
    }

    def "Should get simple password reset method when user has no maiden name but has first and last name"() {
        given:
        def userData = userDataWithoutMaiden()
        endpoint.saveUser(userData)

        when:
        def resetMethod = endpoint.getPasswordResetMethod(userData.id)

        then:
        resetMethod == SIMPLE
    }

    def "Should not allow to reset password when user has no maiden name, first and last name"() {
        given:
        def userData = userDataWithoutMaidenAndName()
        endpoint.saveUser(userData)

        when:
        def resetMethod = endpoint.getPasswordResetMethod(userData.id)

        then:
        resetMethod == NOT_AVAILABLE
    }

    def "Should not get password reset method when user does not exist"() {
        when:
        endpoint.getPasswordResetMethod(123)

        then:
        thrown NotFoundException
    }
}
