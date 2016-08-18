package io.gab.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

@Path("/rest/private")
public class PrivateInfo {

  @GET
  @Path("/")
  public String get() {
    Subject currentUser = SecurityUtils.getSubject();

    Session session = currentUser.getSession();

    return "private";
  }

  @GET
  @Path("/async")
  public void submit(final @Suspended AsyncResponse response) {
    new Thread() {
      public void run() {
        Subject currentUser = SecurityUtils.getSubject();
        
        
        response.resume("private-async");
      }
    }.start();
  }

}
