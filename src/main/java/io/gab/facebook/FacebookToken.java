package io.gab.facebook;

import com.github.scribejava.core.model.OAuth2AccessToken;

import io.gab.github.GithubToken;

/**
*
* @author gabriel_titerlea
*/
public class FacebookToken extends GithubToken {

  public FacebookToken(OAuth2AccessToken accessToken, String username,
      boolean rememberMe) {
    super(accessToken, username, rememberMe); 
  }

}
