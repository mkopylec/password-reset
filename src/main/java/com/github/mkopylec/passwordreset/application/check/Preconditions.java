package com.github.mkopylec.passwordreset.application.check;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

public final class Preconditions {

    public static void notFoundIfNull(Object object, String error) {
        if (object == null) {
            throw new NotFoundException(error);
        }
    }

    public static void badRequestIfFalse(boolean condition, String error) {
        if (!condition) {
            throw new BadRequestException(error);
        }
    }

    private Preconditions() {
    }
}
