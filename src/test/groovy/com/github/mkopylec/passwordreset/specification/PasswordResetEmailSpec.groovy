package com.github.mkopylec.passwordreset.specification

import com.github.mkopylec.passwordreset.BasicSpec
import spock.lang.Unroll

import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.FULL
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.NOT_AVAILABLE
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.SIMPLE
import static com.github.mkopylec.passwordreset.utils.DtoFactory.completeUserData
import static com.github.mkopylec.passwordreset.utils.DtoFactory.resetDataFor
import static com.github.mkopylec.passwordreset.utils.DtoFactory.userDataWithoutMaidenAndName

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
        getMailSubject(userData.email).contains(userData.firstName)
        getMailSubject(userData.email).contains(userData.lastName)
        getMailContent(userData.email).contains(resetData.resetUrl)

        when:
        response = getEndpoint().sendPasswordResetEmail(userData.email, resetData)

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
        getEndpoint().saveUser(userData)

        def resetData = resetDataFor(userData, resetMethod)

        when:
        def response = getEndpoint().sendPasswordResetEmail(userData.username, resetData)

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
        getEndpoint().saveUser(userData)

        def resetData = resetDataFor(userData, resetUrl)

        when:
        def response = getEndpoint().sendPasswordResetEmail(userData.username, resetData)

        then:
        response.status == 400
        getMailSubject(userData.email) == null
        getMailContent(userData.email) == null

        where:
        resetUrl << [null, '', '  ']
    }
}
