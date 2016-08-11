package io.gab.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/private")
public class PrivateInfo {

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public String get() {
    return "{}";
  }
  
}
