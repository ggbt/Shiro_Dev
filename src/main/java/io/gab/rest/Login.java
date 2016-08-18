package io.gab.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/rest/login")
public class Login {
  
  @GET
  @Path("/")
  public void doLogin(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
    response.getWriter().write("Login form");
  }
}
