package com.github.mkopylec.passwordreset.infrastructure.jersey;

import org.slf4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.status;
import static org.slf4j.LoggerFactory.getLogger;

@Provider
class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger log = getLogger(ConstraintViolationExceptionMapper.class);

    @Override
    public Response toResponse(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errors = new ArrayList<>(violations.size());
        if (violations.isEmpty()) {
            errors.add("Unexpected validation error");
            log.warn("Validation error", e);
        } else {
            for (ConstraintViolation<?> violation : violations) {
                errors.add(violation.getMessage());
                log.warn("Validation error: {}. Violated element: {}='{}'",
                        violation.getMessage(), violation.getPropertyPath(), violation.getInvalidValue()
                );
            }
        }
        return status(BAD_REQUEST).entity(errors).build();
    }
}
