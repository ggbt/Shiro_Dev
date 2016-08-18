package io.gab.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/rest/login")
public class Login {
  
  @GET
  @Path("/")
  @Produces("text/plain")
  public String doLogin(@Context HttpServletRequest request, @Context HttpServletResponse response) {
    return "Login form";
  }
}
