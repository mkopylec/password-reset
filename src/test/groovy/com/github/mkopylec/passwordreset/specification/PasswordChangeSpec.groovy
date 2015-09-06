package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import com.github.mkopylec.passwordreset.api.UserEndpoint
import com.github.mkopylec.passwordreset.api.dto.Password
import spock.lang.Unroll

import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData

class PasswordChangeSpec extends BasicSpec<UserEndpoint> {

    def "Should change user password"() {
        given:
        def userData = completeUserData()
        getEndpoint().saveUser(userData)

        def password = new Password(text: 't0p_s3cr3t')

        when:
        def response = getEndpoint().changePassword(userData.id, password)

        then:
        response.status == 200
        //TODO Sprawdzic czy haslo zostalo zmienione
//        findInMongoDB(userData.id, entityClass).password == 't0p_s3cr3t hash'
    }

    @Unroll
    def "Should not change user password when password is #passwordText"() {
        given:
        def userData = completeUserData()
        getEndpoint().saveUser(userData)

        def password = new Password(text: passwordText)

        when:
        def response = getEndpoint().changePassword(userData.id, password)

        then:
        response.status == 400

        where:
        passwordText << [null, '', '  ']
    }

    def "Should not change user password when user does not exist"() {
        given:
        def password = new Password(text: 'password_text')

        when:
        def response = getEndpoint().changePassword(123, password)

        then:
        response.status == 404
    }
}
