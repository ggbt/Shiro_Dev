package io.gab.facebook;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;

import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

@Path("/rest/social/facebook")
public class FacebookFlow {

  /**
   * Login service
   */
  static OAuth20Service service = new ServiceBuilder()
      .apiKey("596223327226830")
      .apiSecret("28027e8309864045e321a398c07c0e74")
      .callback("http://localhost:8081/shiro/rest/social/facebook/callback") // Try oob
      .build(FacebookApi.instance());
  
  static URI authorizationUrl = URI.create(service.getAuthorizationUrl());
  
  @GET
  @Path("/")
  @Produces(MediaType.TEXT_PLAIN)
  public Response redirectToAuthorization() {
    // Pass redirect URI here from request.getParameter("redirectUri") somehow. 
    // To go back to wherever you were when you were redirected to the login page.
    return Response.seeOther(authorizationUrl).build();
  }
  
  @GET
  @Path("/callback")
  @Produces(MediaType.TEXT_PLAIN)
  public Response callback(@Context HttpServletRequest request) {
    try {
      String code = request.getParameter("code");
      
      OAuth2AccessToken accessToken = service.getAccessToken(code);
      
      SecurityUtils.getSubject().login(new FacebookToken(accessToken, "ggbt", false));
      
      boolean permitted = SecurityUtils.getSubject().isPermitted("admin");
      
      System.out.println(accessToken);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return Response.seeOther(URI.create("http://localhost:8081/shiro")).build();
  }
}
