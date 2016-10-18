package io.gab.proper;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Doc here.
 * @author gabriel_titerlea
 */
public class BearerToken implements AuthenticationToken {
  private static final long serialVersionUID = 5977284095394349848L;
  
  Object principal;
  Object credentials;
  
  public BearerToken(Object principal, Object credentials) {
    this.principal = principal;
    this.credentials = credentials;
  }
  
  @Override
  public Object getPrincipal() {
    return principal;
  }
  @Override
  public Object getCredentials() {
    return credentials;
  }
}
