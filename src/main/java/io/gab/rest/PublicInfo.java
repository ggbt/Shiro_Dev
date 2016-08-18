package io.gab.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/rest/public")
public class PublicInfo {

  @GET
  @Path("/")
  public String get() {
    return "public";
  }
  
}
