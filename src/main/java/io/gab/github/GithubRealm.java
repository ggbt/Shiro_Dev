package io.gab.github;


import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class GithubRealm extends AuthorizingRealm {

  public GithubRealm() {
    // Calling SecurityUtils.getSubject().login() with a token of this class will be handled by this realm. 
    // Maybe have a SocialToken. Looks like I might be able to generalize all of them in a class.
    // The GitHub realm only supports login attempts made with a GithubToken 
    setAuthenticationTokenClass(GithubToken.class);
  }
  
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    // Called when SecurityUtils.getSubject().isPermitted is called
    Object principal = getAvailablePrincipal(principals);
    
    Set<String> roles = getRolesForPrincipal(principal);
    Set<String> permissions = getPermissionsForRoles(roles);
    
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
    info.setStringPermissions(permissions);
    return info;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    // The principal should be some id which is associated with multiple social accounts.
    // Check the database to see if the token credentials match (The password)
    return new SimpleAccount(token.getPrincipal(), token.getCredentials(), this.getClass().getName());
    //return null;
  }
  
  private Set<String> getPermissionsForRoles(Set<String> roles) {
    // Query database for the permissions of these roles
    
    Set<String> permissions = new HashSet<String>();
    permissions.add("read");
    permissions.add("write");
    
    return permissions; 
  }


  private Set<String> getRolesForPrincipal(Object principal) {
    Set<String> roles = new HashSet<String>();
    roles.add("admin");
    return roles;
  }
}
