package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import spock.lang.Unroll

import static com.github.mkopylec.passwordreset.DtoFactory.completeUserData
import static com.github.mkopylec.passwordreset.DtoFactory.resetDataFor
import static com.github.mkopylec.passwordreset.DtoFactory.userDataWithoutMaidenAndName

class PasswordResetEmailSpec extends BasicSpec {

    @Unroll
    def "Should send password reset e-mail to user when reset method is #resetMethod"() {
        given:
        def userData = completeUserData()
        getEndpoint().saveUser(userData)

        def resetData = resetDataFor(userData, resetMethod)

        when:
        def response = getEndpoint().sendPasswordResetEmail(userData.username, resetData)

        then:
        response.status == 200

        when:
        response = getEndpoint().sendPasswordResetEmail(userData.email, resetData)

        then:
        response.status == 200

        where:
        resetMethod << ['FULL', 'SIMPLE']
    }

    def "Should not send password reset e-mail to user when reset method is not available"() {
        given:
        def userData = userDataWithoutMaidenAndName()
        getEndpoint().saveUser(userData)

        def resetData = resetDataFor(userData, 'NOT_AVAILABLE')

        when:
        def response = getEndpoint().sendPasswordResetEmail(userData.username, resetData)

        then:
        response.status == 400

        when:
        response = getEndpoint().sendPasswordResetEmail(userData.email, resetData)

        then:
        response.status == 400
    }
}
