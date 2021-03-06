package io.gab.github;

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

import com.github.scribejava.apis.GitHubApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

@Path("/rest/social/github")
public class GithubFlow {
  
  /**
   * Login service
   */
  static OAuth20Service service = new ServiceBuilder()
      .apiKey("87c846572b2b90a28177")
      .apiSecret("68a1acace949dc6c4d3a270805f035f945a2ab25")
      .callback("http://localhost:8081/shiro/rest/social/github/callback") // Try oob
      .build(GitHubApi.instance());
  
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
      
      // Using access token get user id. And create an account for that user if it does not exist.
      // The access token should be telling who this user is. Need to get the user from the database here.
      // But when getting a user from the database with user and password I might need to create the token manually as well.
      // Look into how the Principal is created for user-password authentication when using a jdbc realm.
      
      SecurityUtils.getSubject().login(new GithubToken(accessToken, "ggbt", false));
      
      boolean permitted = SecurityUtils.getSubject().isPermitted("admin");
      
      System.out.println(accessToken);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return Response.seeOther(URI.create("http://localhost:8081/shiro")).build();
  }
}
