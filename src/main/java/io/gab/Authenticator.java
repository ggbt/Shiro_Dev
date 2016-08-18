package io.gab;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

public class Authenticator implements org.apache.shiro.authc.Authenticator {

  @Override
  public AuthenticationInfo authenticate(AuthenticationToken token) throws AuthenticationException {
    return null; // not here
  }
  
}
