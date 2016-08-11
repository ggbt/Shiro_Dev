package io.gab;

import io.gab.rest.PrivateInfo;
import io.gab.rest.PublicInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Application extends javax.ws.rs.core.Application {
  private static final List<Class<?>> RESOURCES = Arrays.<Class<?>>asList(
      PublicInfo.class,
      PrivateInfo.class
  );
      
  /**
   * The list of all application classes.
   */
  private static final Set<Class<?>> CLASSES = new HashSet<Class<?>>();
  static {
    CLASSES.addAll(RESOURCES);
  }
  
  @Override
  public Set<Class<?>> getClasses() {
    return CLASSES;
  }
}
