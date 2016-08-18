package io.gab.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/rest/private")
public class PrivateInfo {

  @GET
  @Path("/")
  public String get() {
    return "private";
  }
  
}
