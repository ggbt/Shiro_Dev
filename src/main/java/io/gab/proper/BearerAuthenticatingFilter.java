package io.gab.proper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

public class BearerAuthenticatingFilter extends AuthenticatingFilter {
  
  @Override
  protected boolean isAccessAllowed(ServletRequest request,
      ServletResponse response, Object mappedValue) {
    // Shiro calls this method and the default implementation checks whether the subject is authenticated.
    // It will never be, so overwriting the default method to prevent unnecessary method calls.
    return false;
  }
  
  @Override
  protected AuthenticationToken createToken(ServletRequest request,
      ServletResponse response) throws Exception {
    // Simulate extracting token. Hard code a token extraction here.
    // But if I return null an Exception is thrown. I need to check the token in the realm.
    // And since this will be no sessionn holding the getAuthentication info method will always be called.

    // I should have a not authenticated token to pass if the header is missing.
    
    HttpServletRequest httpReq = (HttpServletRequest) request;
    String authentication = httpReq.getHeader("Authentication");
    
    // parse the authentication header for the credentials.
    
    // Extract the token from the header
    return new BearerToken("principal", "credentials");
  }

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    String authenticationHeader = ((HttpServletRequest) request).getHeader("Authentication");
    
    if (authenticationHeader != null || true) {
      // Authentication headers requests should not create sessions.
      request.setAttribute(DefaultSubjectContext.SESSION_CREATION_ENABLED, Boolean.FALSE);
      
      executeLogin(request, response);
    }
    
    // Allow the request to go the next filters.
    return true;
  }

}
