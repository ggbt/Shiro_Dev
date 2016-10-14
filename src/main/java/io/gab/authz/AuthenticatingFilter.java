package io.gab.authz;

import io.gab.github.GithubToken;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;

import com.github.scribejava.core.model.OAuth2AccessToken;

public class AuthenticatingFilter extends org.apache.shiro.web.filter.authc.AuthenticatingFilter {

  @Override
  protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
    // Extract the information from the request. If the request contains the HEADER build an access token and all should be well.
    // Have both authc and this filter and whoever wins first, wins!
    return new GithubToken(new OAuth2AccessToken("token"), "ggbt", false);
  }

  protected boolean onAccessDenied(ServletRequest request,
      ServletResponse response) throws Exception {
    
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    String header = httpRequest.getHeader("OXY"); // I actually need to authenticate the user with the id. I need to create an access token with the id and the password set to permit or something.
    
    if (header != null) {
      boolean loggedIn = executeLogin(request, response);
      if (!loggedIn) {
        httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return false;  
      }
    }
    return true;
  }

}
