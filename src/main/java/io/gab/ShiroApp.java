package io.gab;

import io.gab.facebook.FacebookFlow;
import io.gab.github.GithubFlow;
import io.gab.rest.PrivateInfo;
import io.gab.rest.PublicInfo;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest/")
public class ShiroApp extends Application {
  public ShiroApp() {
    System.setProperty("java.net.useSystemProxies", "true");
  }
  
  @Override
  public Set<Class<?>> getClasses() {
    HashSet<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(PublicInfo.class);
    classes.add(PrivateInfo.class);
    classes.add(GithubFlow.class);
    classes.add(FacebookFlow.class);
    
    return classes;
  }
}
