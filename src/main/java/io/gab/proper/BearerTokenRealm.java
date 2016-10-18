package io.gab.proper;

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

public class BearerTokenRealm extends AuthorizingRealm {

  public BearerTokenRealm() {
    setAuthenticationTokenClass(BearerToken.class);
  }
  
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    Object principal = getAvailablePrincipal(principals);
    
    Set<String> roles = getRolesForPrincipal(principal);
    Set<String> permissions = getPermissionsForRoles(roles);
    
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
    info.setStringPermissions(permissions);
    return null;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    return new SimpleAccount(token.getPrincipal(), token.getCredentials(), this.getClass().getName());
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
