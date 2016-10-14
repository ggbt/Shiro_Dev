package io.gab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.Part;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

public class BadassServletRequest implements HttpServletRequest{

  private HttpServletRequest delegate;

  public Object getAttribute(String name) {
    Object attribute = delegate.getAttribute(name);
    return attribute;
  }

  public String getAuthType() {
    String authType = delegate.getAuthType();
    return authType;
  }

  public Cookie[] getCookies() {
    Cookie[] cookies = delegate.getCookies();
    return new Cookie[0];
  }

  public Enumeration<String> getAttributeNames() {
    return delegate.getAttributeNames();
  }

  public long getDateHeader(String name) {
    return delegate.getDateHeader(name);
  }

  public String getCharacterEncoding() {
    return delegate.getCharacterEncoding();
  }

  public void setCharacterEncoding(String env)
      throws UnsupportedEncodingException {
    delegate.setCharacterEncoding(env);
  }

  public String getHeader(String name) {
    String header = delegate.getHeader(name);
    return header;
  }

  public int getContentLength() {
    return delegate.getContentLength();
  }

  public String getContentType() {
    return delegate.getContentType();
  }

  public Enumeration<String> getHeaders(String name) {
    final Enumeration<String> headers = delegate.getHeaders(name);
      
    Enumeration<String> myHeaders = new Enumeration<String>() {

      @Override
      public boolean hasMoreElements() {
        return headers.hasMoreElements();
      }

      @Override
      public String nextElement() {
        String nextElement = headers.nextElement();
        if (nextElement.startsWith("OXYGEN_JSESSIONID")) {
          return "";
        }
        return nextElement;
      }
      
    };
    
    return myHeaders;
  }
  
  public Enumeration<String> getHeaderNames() {
    final Enumeration<String> headerNames = delegate.getHeaderNames();
    
    Enumeration<String> myHeaderNames = new Enumeration<String>() {
      @Override
      public boolean hasMoreElements() {
        return headerNames.hasMoreElements();
      }

      @Override
      public String nextElement() {
        String nextElement = headerNames.nextElement();
        
        if (nextElement.startsWith("cookie")) {
          return "";
        }
        
        return nextElement;
      }
    };
    
    return myHeaderNames;
  }

  public ServletInputStream getInputStream() throws IOException {
    return delegate.getInputStream();
  }

  public String getParameter(String name) {
    String parameter = delegate.getParameter(name);
    return parameter;
  }

  public int getIntHeader(String name) {
    int intHeader = delegate.getIntHeader(name);
    return intHeader;
  }

  public Enumeration<String> getParameterNames() {
    Enumeration<String> parameterNames = delegate.getParameterNames();
    return parameterNames;
  }

  public String[] getParameterValues(String name) {
    String[] parameterValues = delegate.getParameterValues(name);
    return parameterValues;
  }

  public String getMethod() {
    return delegate.getMethod();
  }

  public String getPathInfo() {
    return delegate.getPathInfo();
  }

  public Map<String, String[]> getParameterMap() {
    Map<String, String[]> parameterMap = delegate.getParameterMap();
    return parameterMap;
  }

  public String getProtocol() {
    return delegate.getProtocol();
  }

  public String getPathTranslated() {
    return delegate.getPathTranslated();
  }

  public String getScheme() {
    return delegate.getScheme();
  }

  public String getServerName() {
    return delegate.getServerName();
  }

  public String getContextPath() {
    return delegate.getContextPath();
  }

  public int getServerPort() {
    return delegate.getServerPort();
  }

  public BufferedReader getReader() throws IOException {
    return delegate.getReader();
  }

  public String getQueryString() {
    String queryString = delegate.getQueryString();
    return queryString;
  }

  public String getRemoteAddr() {
    return delegate.getRemoteAddr();
  }

  public String getRemoteUser() {
    String remoteUser = delegate.getRemoteUser();
    return remoteUser;
  }

  public String getRemoteHost() {
    return delegate.getRemoteHost();
  }

  public boolean isUserInRole(String role) {
    return delegate.isUserInRole(role);
  }

  public void setAttribute(String name, Object o) {
    delegate.setAttribute(name, o);
  }

  public Principal getUserPrincipal() {
    return delegate.getUserPrincipal();
  }

  public String getRequestedSessionId() {
    return delegate.getRequestedSessionId();
  }

  public void removeAttribute(String name) {
    delegate.removeAttribute(name);
  }

  public String getRequestURI() {
    return delegate.getRequestURI();
  }

  public Locale getLocale() {
    return delegate.getLocale();
  }

  public Enumeration<Locale> getLocales() {
    return delegate.getLocales();
  }

  public StringBuffer getRequestURL() {
    return delegate.getRequestURL();
  }

  public boolean isSecure() {
    return delegate.isSecure();
  }

  public RequestDispatcher getRequestDispatcher(String path) {
    return delegate.getRequestDispatcher(path);
  }

  public String getServletPath() {
    return delegate.getServletPath();
  }

  public HttpSession getSession(boolean create) {
    // Culprit here?
    /*final HttpSession session = delegate.getSession(create);
    
    HttpSession mySession = new HttpSession() {

      @Override
      public long getCreationTime() {
        return session.getCreationTime();
      }

      @Override
      public String getId() {
        return session.getId() + "Bye";
      }

      @Override
      public long getLastAccessedTime() {
        return session.getLastAccessedTime();
      }

      @Override
      public ServletContext getServletContext() {
        return session.getServletContext();
      }

      @Override
      public void setMaxInactiveInterval(int interval) {
        session.setMaxInactiveInterval(interval);
      }

      @Override
      public int getMaxInactiveInterval() {
        return session.getMaxInactiveInterval();
      }

      @Override
      public HttpSessionContext getSessionContext() {
        return session.getSessionContext();
      }

      @Override
      public Object getAttribute(String name) {
        Object attribute = session.getAttribute(name);
        
        if ("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY".equals(name)) {
          final SimplePrincipalCollection coll = (SimplePrincipalCollection) attribute;
          
          SimplePrincipalCollection myColl = new SimplePrincipalCollection() {
            public Object getPrimaryPrincipal() {
              return coll.getPrimaryPrincipal();
            }
            
            @Override
            public List asList() {
              return coll.asList();
            }
            
            @Override
            public Set asSet() {
              return coll.asSet();
            }
            
            @Override
            public void add(Object principal, String realmName) {
              coll.add(principal, realmName);
            }
            
            @Override
            public void addAll(Collection principals, String realmName) {
              // TODO Auto-generated method stub
              coll.addAll(principals, realmName);
            }
            
            @Override
            public void addAll(PrincipalCollection principals) {
              // TODO Auto-generated method stub
              coll.addAll(principals);
            }
            
            @Override
            public <T> Collection<T> byType(Class<T> type) {
              // TODO Auto-generated method stub
              return coll.byType(type);
            }
            
            @Override
            public void clear() {
              coll.clear();
            }

            @Override
            public Collection fromRealm(String realmName) {
              // TODO Auto-generated method stub
              return coll.fromRealm(realmName);
            }
            
            @Override
            protected Collection getPrincipalsLazy(String realmName) {
              try {
                Method getPrincipalsLazy = SimplePrincipalCollection.class.getDeclaredMethod("getPrincipalsLazy", String.class);
                return (Collection) getPrincipalsLazy.invoke(coll, realmName);
              } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
              return null;
            }
            
            @Override
            public Set<String> getRealmNames() {
              return coll.getRealmNames();
            }
            
            @Override
            public boolean isEmpty() {
              // TODO Auto-generated method stub
              return coll.isEmpty();
            }
            
            @Override
            public Iterator iterator() {
              return coll.iterator();
            }
            
            @Override
            public <T> T oneByType(Class<T> type) {
              return coll.oneByType(type);
            }
            
            @Override
            public String toString() {
              return coll.toString();
            }
            @Override
            public int hashCode() {
              return coll.hashCode();
            }
            @Override
            public boolean equals(Object o) {
              return coll.equals(o);
            }
          };
          
          return myColl;
        }
        
        return attribute;
      }

      @Override
      public Object getValue(String name) {
        return session.getValue(name);
      }

      @Override
      public Enumeration<String> getAttributeNames() {
        return session.getAttributeNames();
      }

      @Override
      public String[] getValueNames() {
        return session.getValueNames();
      }

      @Override
      public void setAttribute(String name, Object value) {
        session.setAttribute(name, value);
      }

      @Override
      public void putValue(String name, Object value) {
        session.putValue(name, value);
      }

      @Override
      public void removeAttribute(String name) {
        session.removeAttribute(name);
      }

      @Override
      public void removeValue(String name) {
        session.removeValue(name);
      }

      @Override
      public void invalidate() {
        session.invalidate();
      }

      @Override
      public boolean isNew() {
        return session.isNew();
      }
      
    };
*/    
    return delegate.getSession(create);
  }

  public String getRealPath(String path) {
    return delegate.getRealPath(path);
  }

  public int getRemotePort() {
    return delegate.getRemotePort();
  }

  public String getLocalName() {
    return delegate.getLocalName();
  }

  public String getLocalAddr() {
    return delegate.getLocalAddr();
  }

  public HttpSession getSession() {
    return delegate.getSession();
  }

  public int getLocalPort() {
    return delegate.getLocalPort();
  }

  public ServletContext getServletContext() {
    return delegate.getServletContext();
  }

  public boolean isRequestedSessionIdValid() {
    return delegate.isRequestedSessionIdValid();
  }

  public AsyncContext startAsync() throws IllegalStateException {
    return delegate.startAsync();
  }

  public boolean isRequestedSessionIdFromCookie() {
    return delegate.isRequestedSessionIdFromCookie();
  }

  public boolean isRequestedSessionIdFromURL() {
    return delegate.isRequestedSessionIdFromURL();
  }

  public boolean isRequestedSessionIdFromUrl() {
    return delegate.isRequestedSessionIdFromUrl();
  }

  public boolean authenticate(HttpServletResponse response) throws IOException,
      ServletException {
    return delegate.authenticate(response);
  }

  public AsyncContext startAsync(ServletRequest servletRequest,
      ServletResponse servletResponse) throws IllegalStateException {
    return delegate.startAsync(servletRequest, servletResponse);
  }

  public void login(String username, String password) throws ServletException {
    delegate.login(username, password);
  }

  public void logout() throws ServletException {
    delegate.logout();
  }

  public Collection<Part> getParts() throws IOException, ServletException {
    return delegate.getParts();
  }

  public boolean isAsyncStarted() {
    return delegate.isAsyncStarted();
  }

  public Part getPart(String name) throws IOException, ServletException {
    return delegate.getPart(name);
  }

  public boolean isAsyncSupported() {
    return delegate.isAsyncSupported();
  }

  public AsyncContext getAsyncContext() {
    return delegate.getAsyncContext();
  }

  public DispatcherType getDispatcherType() {
    return delegate.getDispatcherType();
  }

  public BadassServletRequest(HttpServletRequest request) {
    this.delegate = request;
  }

  
}
