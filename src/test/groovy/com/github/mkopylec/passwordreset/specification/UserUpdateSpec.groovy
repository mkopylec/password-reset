package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import com.github.mkopylec.passwordreset.api.UserEndpoint
import spock.lang.Unroll

import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData

class UserUpdateSpec extends BasicSpec<UserEndpoint> {

    def "Should save user"() {
        given:
        def userData = completeUserData()

        when:
        def response = endpoint.saveUser(userData)

        then:
        response.status == 200
        //TODO Sprawdzic czy dane sie zgadzaja
//        findInMongoDB(userData.id, entityClass) matches userData
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
        //TODO Sprawdzic czy dane uzytkownika sie nie zapisaly
//        findInMongoDB(userData.id, entityClass) == null

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
