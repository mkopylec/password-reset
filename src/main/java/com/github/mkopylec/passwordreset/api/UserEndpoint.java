package com.github.mkopylec.passwordreset.api;

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
public interface UserEndpoint {

    @GET
    @Path("{loginOrEmail}/passwordResetMethod")
    ResetMethod getPasswordResetMethod(@PathParam("loginOrEmail") String loginOrEmail);

    @PUT
    @Path("{loginOrEmail}/passwordResetData")
    Response sendPasswordResetEmail(@PathParam("loginOrEmail") String loginOrEmail, ResetData resetData);

    @PUT
    @Path("{loginOrEmail}/password")
    Response changePassword(@PathParam("loginOrEmail") String loginOrEmail, NewPassword newPassword);

    @PUT
    Response saveUser(UserData userData);
}
