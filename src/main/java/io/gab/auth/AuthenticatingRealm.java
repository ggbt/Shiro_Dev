package io.gab.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;

public class AuthenticatingRealm extends org.apache.shiro.realm.AuthenticatingRealm {

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    SimpleAccount account = new SimpleAccount();
    return account;
  }

}
