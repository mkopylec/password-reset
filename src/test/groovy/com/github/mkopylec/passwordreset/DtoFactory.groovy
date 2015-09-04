package com.github.mkopylec.passwordreset

import com.github.mkopylec.passwordreset.api.ResetData
import com.github.mkopylec.passwordreset.api.UserData

class DtoFactory {

    static UserData completeUserData() {
        return new UserData(
                id: 666,
                username: 'some_user',
                email: 'ddd.workshop.mial@gmail.com',
                maidenName: 'mama',
                firstName: 'James',
                lastName: 'Bond'
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

    static ResetData resetDataFor(UserData userData, String resetMethod) {
        return new ResetData(
                maidenName: userData.maidenName,
                resetMethod: resetMethod,
                resetUrl: 'http://redirect.url/'
        )
    }
}
