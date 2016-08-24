package io.gab.rest;

import java.io.IOException;
import java.net.URI;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.scribejava.apis.GitHubApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

@Path("/rest/social")
public class GithubFlow {
  
  /**
   * Login service
   */
  static OAuth20Service service = new ServiceBuilder()
      .apiKey("87c846572b2b90a28177")
      .apiSecret("68a1acace949dc6c4d3a270805f035f945a2ab25")
      .callback("http://localhost:8081/shiro/rest/social/callback") // Try oob
      .build(GitHubApi.instance());
  
  static URI authorizationUrl = URI.create(service.getAuthorizationUrl());
  
  @GET
  @Path("/github")
  @Produces(MediaType.TEXT_PLAIN)
  public Response redirectToAuthorization() {
    return Response.seeOther(authorizationUrl).build();
  }
  
  @GET
  @Path("/callback")
  @Produces(MediaType.TEXT_PLAIN)
  public Response callback(@Context HttpServletRequest request) {
    try {
      // String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
      String code = request.getParameter("code");
      
      OAuth2AccessToken accessToken = service.getAccessToken(code);
      System.out.println(accessToken);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return Response.seeOther(URI.create("http://localhost:8081/shiro")).build();
  }
}
