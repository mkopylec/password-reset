package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import com.github.mkopylec.passwordreset.api.UserEndpoint
import com.github.mkopylec.passwordreset.api.dto.ResetData
import spock.lang.Unroll

import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.FULL
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.NOT_AVAILABLE
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.SIMPLE
import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData
import static com.github.mkopylec.passwordreset.utils.DtoFactory.resetDataFor
import static com.github.mkopylec.passwordreset.utils.DtoFactory.userDataWithoutMaidenAndName

class PasswordResetEmailSpec extends BasicSpec<UserEndpoint> {

    @Unroll
    def "Should send password reset e-mail to user when reset method is #resetMethod"() {
        given:
        def userData = completeUserData()
        endpoint.saveUser(userData)

        def resetData = resetDataFor(userData, resetMethod)

        when:
        def response = endpoint.sendPasswordResetEmail(userData.id, resetData)

        then:
        response.status == 200
        getMailSubject(userData.email).contains(userData.firstName)
        getMailSubject(userData.email).contains(userData.lastName)
        getMailContent(userData.email).contains(resetData.resetUrl)

        where:
        resetMethod << [FULL, SIMPLE]
    }

    @Unroll
    def "Should not send password reset e-mail to user when reset method is #resetMethod"() {
        given:
        def userData = userDataWithoutMaidenAndName()
        endpoint.saveUser(userData)

        def resetData = resetDataFor(userData, resetMethod)

        when:
        def response = endpoint.sendPasswordResetEmail(userData.id, resetData)

        then:
        response.status == 400
        getMailSubject(userData.email) == null
        getMailContent(userData.email) == null

        where:
        resetMethod << [null, NOT_AVAILABLE]
    }

    @Unroll
    def "Should not send password reset e-mail to user when reset URL is #resetUrl"() {
        given:
        def userData = userDataWithoutMaidenAndName()
        endpoint.saveUser(userData)

        def resetData = resetDataFor(userData, resetUrl)

        when:
        def response = endpoint.sendPasswordResetEmail(userData.id, resetData)

        then:
        response.status == 400
        getMailSubject(userData.email) == null
        getMailContent(userData.email) == null

        where:
        resetUrl << [null, '', '  ']
    }

    def "Should not send password reset e-mail to user when user does not exist"() {
        given:
        def resetData = new ResetData(resetMethod: FULL, maidenName: 'maiden', resetUrl: 'url')

        when:
        def response = endpoint.sendPasswordResetEmail(123, resetData)

        then:
        response.status == 404
    }

    def "Should not send password reset e-mail when no reset data was provided"() {
        when:
        endpoint.sendPasswordResetEmail(123, null)

        then:
        thrown IllegalArgumentException
    }
}
