package io.gab.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/rest/github")
public class Github {
  @GET
  @Path("/")
  public String get() {
    return "github";
  }
}

