package io.gab.rest;

import java.io.IOException;
import java.net.URI;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.scribejava.apis.GitHubApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.extractors.TokenExtractor;
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
      .callback("http://localhost://8081/shiro/social/callback") // Try oob
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
  public Response authenticate(@Context HttpServletRequest request) {
    TokenExtractor<OAuth2AccessToken> accessTokenExtractor = service.getApi().getAccessTokenExtractor();
    
    try {
      String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
      OAuth2AccessToken accessToken = accessTokenExtractor.extract(requestBody);
      
      // Actually I should be extracting the code to exchange it for an access token here. Do this first thing
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return null;
  }
}
