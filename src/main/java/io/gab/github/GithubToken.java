package io.gab.github;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 *
 * @author gabriel_titerlea
 */
public class GithubToken implements AuthenticationToken, RememberMeAuthenticationToken {
  /**
   * The github OAuth access token.
   */
  private OAuth2AccessToken accessToken;
  
  /**
   * The github username.
   */
  private String username;

  /**
   * If <code>true</code> remember me!
   */
  private boolean rememberMe;

  public GithubToken(OAuth2AccessToken accessToken, String username, boolean rememberMe) {
    this.accessToken = accessToken;
    this.username = username;
    
    this.rememberMe = rememberMe;
  }
  
  @Override
  public Object getPrincipal() {
    // Return username, picture, whatever information I can get.
    return username;
  }

  @Override
  public Object getCredentials() {
    return accessToken;
  }

  @Override
  public boolean isRememberMe() {
    return rememberMe;
  }

}
