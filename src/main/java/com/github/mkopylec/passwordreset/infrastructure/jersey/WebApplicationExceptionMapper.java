package com.github.mkopylec.passwordreset.infrastructure.jersey;

import org.slf4j.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.status;
import static org.slf4j.LoggerFactory.getLogger;

@Provider
class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    private static final Logger log = getLogger(WebApplicationExceptionMapper.class);

    @Override
    public Response toResponse(WebApplicationException e) {
        Response response = e.getResponse();
        log.warn("HTTP error: {} ({})", response.getStatus(), response.getStatusInfo());
        return status(response.getStatusInfo()).entity(e.getMessage()).build();
    }
}
