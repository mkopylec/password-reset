package com.github.mkopylec.passwordreset.api;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(METHOD)
@Retention(RUNTIME)
@HttpMethod("PATCH")
public @interface PATCH {

}
