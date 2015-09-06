package com.github.mkopylec.passwordreset.application.check;

import javax.ws.rs.NotFoundException;

public final class Preconditions {

    public static void notFoundIfNull(Object object, String error) {
        if (object == null) {
            throw new NotFoundException(error);
        }
    }

    private Preconditions() {
    }
}
