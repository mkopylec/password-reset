package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import com.github.mkopylec.passwordreset.api.PasswordResetEndpoint
import com.github.mkopylec.passwordreset.domain.user.User
import spock.lang.Unroll

import static com.github.mkopylec.passwordreset.assertions.CustomAssertions.assertThat
import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData

class UserUpdateSpec extends BasicSpec<PasswordResetEndpoint> {

    def "Should save user"() {
        given:
        def userData = completeUserData()

        when:
        def response = endpoint.saveUser(userData)

        then:
        response.status == 200

        def user = findInMongoDB(userData.id, User)
        assertThat(user)
                .hasId(userData.id)
                .hasUsername(userData.username)
                .hasHashedPassword(userData.hashedPassword)
                .hasEmail(userData.email)
                .hasMaidenName(userData.maidenName)
                .hasFirstName(userData.firstName)
                .hasLastName(userData.lastName)
    }

    @Unroll
    def "Should not save user when login is '#login' and e-mail is '#email'"() {
        given:
        def userData = completeUserData()
        userData.username = login
        userData.email = email

        when:
        def response = endpoint.saveUser(userData)

        then:
        response.status == 400

        findInMongoDB(userData.id, User) == null

        where:
        login << [null, '', '  ']
        email << ['  ', null, '']
    }

    def "Should not save user when no user data was provided"() {
        when:
        endpoint.saveUser(null)

        then:
        thrown IllegalArgumentException
    }
}
