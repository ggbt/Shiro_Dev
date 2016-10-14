package io.gab;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BadassFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    BadassServletRequest badassHttpReq = new BadassServletRequest((HttpServletRequest) request);
    BadassServletResponse badassHttpResp = new BadassServletResponse((HttpServletResponse) response);
    
    chain.doFilter(badassHttpReq, badassHttpResp);
  }

  @Override
  public void destroy() {
  }

}
