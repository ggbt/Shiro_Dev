package io.gab;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;

public class Authenticator implements org.apache.shiro.authc.Authenticator {

  @Override
  public AuthenticationInfo authenticate(AuthenticationToken token) throws AuthenticationException {
    String user = (String) token.getPrincipal();
    char[] password = (char[]) token.getCredentials();
    
    // Generate salt.
    byte[] salt = "salt".getBytes();
    
    String realmName = "defaultRealm";
    
    // Fake auth
    if ("admin".equals(user) && "admin".equals(new String(password))) {
      ByteSource credentialsSalt = new Sha512Hash(password, salt, 128);
      SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(user, password, credentialsSalt, realmName);
      return authInfo;        
    } else {
      throw new AuthenticationException();
    }
  }
  
}
