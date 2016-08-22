package io.gab;

import io.gab.rest.Github;
import io.gab.rest.PrivateInfo;
import io.gab.rest.PublicInfo;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest/")
public class ShiroApp extends Application {
  @Override
  public Set<Class<?>> getClasses() {
    HashSet<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(PublicInfo.class);
    classes.add(PrivateInfo.class);
    classes.add(Github.class);
    
    return classes;
  }
}
