package com.github.mkopylec.passwordreset.infrastructure.jersey;

import org.slf4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.status;
import static org.slf4j.LoggerFactory.getLogger;

@Provider
class ThrowableMapper implements ExceptionMapper<Throwable> {

    private static final Logger log = getLogger(ThrowableMapper.class);

    @Override
    public Response toResponse(Throwable e) {
        log.error("Unexpected error", e);
        return status(INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    }
}
