package com.github.mkopylec.passwordreset.infrastructure.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("")
class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerClasses(
                //TODO Dodac endpointy RESTowe
                ThrowableMapper.class,
                WebApplicationExceptionMapper.class,
                ConstraintViolationExceptionMapper.class
        );
    }
}
