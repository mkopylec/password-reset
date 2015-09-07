package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import com.github.mkopylec.passwordreset.api.PasswordResetEndpoint
import com.github.mkopylec.passwordreset.api.dto.Password
import com.github.mkopylec.passwordreset.domain.user.User
import spock.lang.Unroll

import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData

class PasswordChangeSpec extends BasicSpec<PasswordResetEndpoint> {

    def "Should change user password"() {
        given:
        def userData = completeUserData()
        endpoint.saveUser(userData)

        def password = new Password(text: 't0p_s3cr3t')

        when:
        def response = endpoint.changePassword(userData.id, password)

        then:
        response.status == 200

        findInMongoDB(userData.id, User).hashedPassword == '324803bd3d0eb6134d6c70e9aff21878cf8ee38be3049259e5541576c8f38128'
    }

    @Unroll
    def "Should not change user password when password is '#passwordText'"() {
        given:
        def userData = completeUserData()
        endpoint.saveUser(userData)

        def password = new Password(text: passwordText)

        when:
        def response = endpoint.changePassword(userData.id, password)

        then:
        response.status == 400

        where:
        passwordText << [null, '', '  ']
    }

    def "Should not change user password when user does not exist"() {
        given:
        def password = new Password(text: 'password_text')

        when:
        def response = endpoint.changePassword(123, password)

        then:
        response.status == 404
    }

    def "Should not change user password when no password was provided"() {
        when:
        endpoint.changePassword(123, null)

        then:
        thrown IllegalArgumentException
    }
}
