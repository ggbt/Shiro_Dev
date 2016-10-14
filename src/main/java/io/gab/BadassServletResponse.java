package io.gab;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class BadassServletResponse implements HttpServletResponse {
  HttpServletResponse delegate;
  
  public void addCookie(Cookie cookie) {
    delegate.addCookie(cookie);
  }

  public boolean containsHeader(String name) {
    return delegate.containsHeader(name);
  }

  public String encodeURL(String url) {
    return delegate.encodeURL(url);
  }

  public String getCharacterEncoding() {
    return delegate.getCharacterEncoding();
  }

  public String encodeRedirectURL(String url) {
    return delegate.encodeRedirectURL(url);
  }

  public String getContentType() {
    return delegate.getContentType();
  }

  public String encodeUrl(String url) {
    return delegate.encodeUrl(url);
  }

  public String encodeRedirectUrl(String url) {
    return delegate.encodeRedirectUrl(url);
  }

  public ServletOutputStream getOutputStream() throws IOException {
    return delegate.getOutputStream();
  }

  public void sendError(int sc, String msg) throws IOException {
    delegate.sendError(sc, msg);
  }

  public PrintWriter getWriter() throws IOException {
    return delegate.getWriter();
  }

  public void sendError(int sc) throws IOException {
    delegate.sendError(sc);
  }

  public void setCharacterEncoding(String charset) {
    delegate.setCharacterEncoding(charset);
  }

  public void sendRedirect(String location) throws IOException {
    delegate.sendRedirect(location);
  }

  public void setDateHeader(String name, long date) {
    delegate.setDateHeader(name, date);
  }

  public void setContentLength(int len) {
    delegate.setContentLength(len);
  }

  public void addDateHeader(String name, long date) {
    delegate.addDateHeader(name, date);
  }

  public void setContentType(String type) {
    delegate.setContentType(type);
  }

  public void setHeader(String name, String value) {
    delegate.setHeader(name, value);
  }

  public void addHeader(String name, String value) {
    delegate.addHeader(name, value);
  }

  public void setBufferSize(int size) {
    delegate.setBufferSize(size);
  }

  public void setIntHeader(String name, int value) {
    delegate.setIntHeader(name, value);
  }

  public void addIntHeader(String name, int value) {
    delegate.addIntHeader(name, value);
  }

  public void setStatus(int sc) {
    delegate.setStatus(sc);
  }

  public int getBufferSize() {
    return delegate.getBufferSize();
  }

  public void flushBuffer() throws IOException {
    delegate.flushBuffer();
  }

  public void setStatus(int sc, String sm) {
    delegate.setStatus(sc, sm);
  }

  public void resetBuffer() {
    delegate.resetBuffer();
  }

  public int getStatus() {
    return delegate.getStatus();
  }

  public boolean isCommitted() {
    return delegate.isCommitted();
  }

  public String getHeader(String name) {
    return delegate.getHeader(name);
  }

  public void reset() {
    delegate.reset();
  }

  public Collection<String> getHeaders(String name) {
    return delegate.getHeaders(name);
  }

  public void setLocale(Locale loc) {
    delegate.setLocale(loc);
  }

  public Collection<String> getHeaderNames() {
    return delegate.getHeaderNames();
  }

  public Locale getLocale() {
    return delegate.getLocale();
  }

  public BadassServletResponse(HttpServletResponse response) {
    delegate = response;
  }
}
