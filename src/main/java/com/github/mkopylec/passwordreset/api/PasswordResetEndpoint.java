package com.github.mkopylec.passwordreset.api;

import com.github.mkopylec.passwordreset.api.dto.Password;
import com.github.mkopylec.passwordreset.api.dto.ResetData;
import com.github.mkopylec.passwordreset.api.dto.ResetMethod;
import com.github.mkopylec.passwordreset.api.dto.UserData;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public interface PasswordResetEndpoint {

    @PUT
    Response saveUser(@NotNull @Valid UserData userData);

    @GET
    @Path("{loginOrEmail}/id")
    long getUserId(@PathParam("loginOrEmail") String loginOrEmail);

    @GET
    @Path("{id}/passwordResetMethod")
    ResetMethod getPasswordResetMethod(@PathParam("id") long id);

    @PATCH
    @Path("{id}/passwordResetData")
    Response sendPasswordResetEmail(@PathParam("id") long id, @NotNull @Valid ResetData resetData);

    @PATCH
    @Path("{id}/password")
    Response changePassword(@PathParam("id") long id, @NotNull @Valid Password password);
}
