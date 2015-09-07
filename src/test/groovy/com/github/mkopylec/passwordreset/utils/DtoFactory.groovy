package com.github.mkopylec.passwordreset.utils

import com.github.mkopylec.passwordreset.api.dto.ResetData
import com.github.mkopylec.passwordreset.api.dto.ResetMethod
import com.github.mkopylec.passwordreset.api.dto.UserData

import static ResetMethod.FULL
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

class DtoFactory {

    static UserData completeUserData() {
        return new UserData(
                id: 666,
                username: 'some_user',
                hashedPassword: 't0p_s3cr3t hash',
                email: 'ddd.workshop.mial@gmail.com',
                maidenName: 'mama',
                firstName: 'James',
                lastName: 'Bond'
        )
    }

    static UserData userDataWithRandomFullName() {
        return new UserData(
                id: 666,
                username: 'some_user',
                hashedPassword: 't0p_s3cr3t hash',
                email: 'ddd.workshop.mial@gmail.com',
                maidenName: 'mama',
                firstName: randomAlphanumeric(16),
                lastName: randomAlphanumeric(16)
        )
    }

    static UserData userDataWithoutMaiden() {
        def userData = completeUserData()
        userData.maidenName = null
        return userData
    }

    static UserData userDataWithoutMaidenAndName() {
        def userData = completeUserData()
        userData.maidenName = null
        userData.firstName = null
        userData.lastName = null
        return userData
    }

    static ResetData resetData() {
        return new ResetData(
                maidenName: 'Maiden',
                resetMethod: FULL,
                resetUrl: 'http://redirect.url/'
        )
    }

    static ResetData resetDataForMethod(UserData userData, ResetMethod resetMethod) {
        return new ResetData(
                maidenName: userData.maidenName,
                resetMethod: resetMethod,
                resetUrl: 'http://redirect.url/'
        )
    }

    static ResetData resetDataForUrl(UserData userData, String resetUrl) {
        return new ResetData(
                maidenName: userData.maidenName,
                resetMethod: FULL,
                resetUrl: resetUrl
        )
    }

    static ResetData resetDataForMaiden(String maidenName) {
        return new ResetData(
                maidenName: maidenName,
                resetMethod: FULL,
                resetUrl: 'http://redirect.url/'
        )
    }
}
