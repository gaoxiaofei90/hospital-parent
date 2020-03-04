package com.llw.hospital.bs.filter.wrapper;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.*;

/**
 * 请求包装类
 * @author huangkeying
 */
public class BufferedHttpRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest req;
    private Map<String,String[]> parameterMap;
    private byte[] bs;
    private boolean bsHasInit=false;

    public BufferedHttpRequestWrapper(HttpServletRequest req){
        super(req);
        this.req=req;
        initParameter();
    }

    @Override
    public Object getAttribute(String name) {
        return this.req.getAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return this.req.getAttributeNames();
    }

    @Override
    public String getCharacterEncoding() {
        return this.req.getCharacterEncoding();
    }

    @Override
    public void setCharacterEncoding(String env)
            throws UnsupportedEncodingException {
        this.req.setCharacterEncoding(env);
    }

    @Override
    public int getContentLength() {
        return this.req.getContentLength();
    }

    public String getContentType() {
        return this.req.getContentType();
    }

    public ServletInputStream getInputStream() throws IOException {
        if(!bsHasInit){
            ServletInputStream input=this.req.getInputStream();


            while(true){
                byte[] bsTmp=new byte[1024];
                int i=input.read(bsTmp);
                if(i==-1){
                    break;
                }else{
                    if(bs==null){
                        bs=Arrays.copyOf(bsTmp,i);
                    }else{
                        byte[] bsNew=new byte[bs.length+i];
                        System.arraycopy(bs, 0, bsNew, 0, bs.length);
                        System.arraycopy(bsTmp, 0, bsNew, bs.length, i);
                        bs=bsNew;
                    }
                }
            }
            if(bs==null)bs=new byte[0];
            bsHasInit=true;
        }
        final ByteArrayInputStream byteStream=new ByteArrayInputStream(bs);
        return new ServletInputStream() {

            @Override
            public int read() {
                return byteStream.read();
            }

//            @Override
//            public void setReadListener(ReadListener readListener) {
//                throw new UnsupportedOperationException();
//            }

//            @Override
//            public boolean isReady() {
//                return true;
//            }

//            @Override
//            public boolean isFinished() {
//                return false;
//            }
        };
    }

    public String getParameter(String name) {
        String[] value=parameterMap.get(name);
        if(value==null)return null;
        return parameterMap.get(name)[0];
    }

    public Enumeration<String> getParameterNames() {
        final Iterator<String> iterator=parameterMap.keySet().iterator();
        return new Enumeration<String>() {
            public boolean hasMoreElements() {
                return iterator.hasNext();
            }
            public String nextElement() {
                return iterator.next();
            }
        };
    }

    public String[] getParameterValues(String name) {
        return parameterMap.get(name);
    }

    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public String getProtocol() {
        return this.req.getProtocol();
    }

    public String getScheme() {
        return this.req.getScheme();
    }

    public String getServerName() {
        return this.req.getServerName();
    }

    public int getServerPort() {
        return this.req.getServerPort();
    }

    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream(),this.req.getCharacterEncoding()));
    }

    public String getRemoteAddr() {
        return this.req.getRemoteAddr();
    }

    public String getRemoteHost() {
        return this.req.getRemoteHost();
    }

    public void setAttribute(String name, Object o) {
        this.req.setAttribute(name, o);
    }

    public void removeAttribute(String name) {
        this.req.removeAttribute(name);
    }

    public Locale getLocale() {
        return this.req.getLocale();
    }

    public Enumeration<Locale> getLocales() {
        return this.req.getLocales();
    }

    public boolean isSecure() {
        return this.req.isSecure();
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return this.req.getRequestDispatcher(path);
    }

    @SuppressWarnings("deprecation")
    public String getRealPath(String path) {
        return this.req.getRealPath(path);
    }

    public int getRemotePort() {
        return this.req.getRemotePort();
    }

    public String getLocalName() {
        return this.req.getLocalName();
    }

    public String getLocalAddr() {
        return this.req.getLocalAddr();
    }

    public int getLocalPort() {
        return this.req.getLocalPort();
    }

    public ServletContext getServletContext() {
        return this.req.getServletContext();
    }

    public AsyncContext startAsync() throws IllegalStateException {
        return this.req.startAsync();
    }

    public AsyncContext startAsync(ServletRequest servletRequest,
                                   ServletResponse servletResponse) throws IllegalStateException {
        return this.req.startAsync(servletRequest, servletResponse);
    }

    public boolean isAsyncStarted() {
        return this.req.isAsyncStarted();
    }

    public boolean isAsyncSupported() {
        return this.req.isAsyncSupported();
    }

    public AsyncContext getAsyncContext() {
        return this.req.getAsyncContext();
    }

    public DispatcherType getDispatcherType() {
        return this.req.getDispatcherType();
    }

    private void initParameter(){
        String getStr=req.getQueryString();
        Map<String,String[]> map1= UrlQueryString.getKeyValueMap(getStr,this.req.getCharacterEncoding());
        try {
            String postStr=null;
            String contentType=req.getContentType();
            if(contentType!=null && contentType.startsWith("application/x-www-form-urlencoded")){
                postStr=getReader().readLine();
            }
            Map<String,String[]> map2= UrlQueryString.getKeyValueMap(postStr,this.req.getCharacterEncoding());
            map1.putAll(map2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            //e.printStackTrace();
        }
        parameterMap=map1;
    }

    static class UrlQueryString{

        private static String[] getParameters(String query){
            if(query==null)return new String[0];
            return query.split("&");
        }

        private static Map<String,String[]> getKeyValueMap(String[] parameters,String encode){
            if(encode==null){
                encode=System.getProperty("file.encoding");
            }
            Map<String,String[]> map= new HashMap<>();
            for(String parameter:parameters){
                String[] KeyValue=parameter.split("=");
                if(KeyValue.length!=2){
//                    System.out.println("["+KeyValue[0]+"]无关键字'='");
                    continue;
                }
                try {
                    String key= URLDecoder.decode(KeyValue[0],encode);
                    String val=URLDecoder.decode(KeyValue[1],encode);
                    if(map.containsKey(key)){
                        String[] ss=map.get(key);
                        String[] newss=new String[ss.length+1];
                        System.arraycopy(newss, 0, ss, 0, ss.length);
                        newss[ss.length]=val;
                        map.put(key, newss);
                    }else{
                        map.put(key, new String[]{val});
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("不支持的编码方式,"+encode);
                }
            }
            return map;
        }

        static Map<String,String[]> getKeyValueMap(String query, String encode){
            String[] parameters=getParameters(query);
            return getKeyValueMap(parameters,encode);
        }
    }

    public String getAuthType() {
        return req.getAuthType();
    }

    public Cookie[] getCookies() {
        return req.getCookies();
    }

    public long getDateHeader(String name) {
        return req.getDateHeader(name);
    }

    public String getHeader(String name) {
        return req.getHeader(name);
    }

    public Enumeration<String> getHeaders(String name) {
        return req.getHeaders(name);
    }

    public Enumeration<String> getHeaderNames() {
        return req.getHeaderNames();
    }

    public int getIntHeader(String name) {
        return req.getIntHeader(name);
    }

    public String getMethod() {
        return req.getMethod();
    }

    public String getPathInfo() {
        return req.getPathInfo();
    }

    public String getPathTranslated() {
        return req.getPathTranslated();
    }

    public String getContextPath() {
        return req.getContextPath();
    }

    public String getQueryString() {
        return req.getQueryString();
    }

    public String getRemoteUser() {
        return req.getRemoteUser();
    }

    public boolean isUserInRole(String role) {
        return req.isUserInRole(role);
    }

    public Principal getUserPrincipal() {
        return req.getUserPrincipal();
    }

    public String getRequestedSessionId() {
        return req.getRequestedSessionId();
    }

    public String getRequestURI() {
        return req.getRequestURI();
    }

    public StringBuffer getRequestURL() {
        return req.getRequestURL();
    }

    public String getServletPath() {
        return req.getServletPath();
    }

    public HttpSession getSession(boolean create) {
        return req.getSession(create);
    }

    public HttpSession getSession() {
        return req.getSession();
    }

//    public String changeSessionId() {
//        return req.changeSessionId();
//    }

    public boolean isRequestedSessionIdValid() {
        return req.isRequestedSessionIdValid();
    }

    public boolean isRequestedSessionIdFromCookie() {
        return req.isRequestedSessionIdFromCookie();
    }

    public boolean isRequestedSessionIdFromURL() {
        return req.isRequestedSessionIdFromURL();
    }

    @SuppressWarnings("deprecation")
    public boolean isRequestedSessionIdFromUrl() {
        return req.isRequestedSessionIdFromUrl();
    }

    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        return req.authenticate(response);
    }

    public void login(String username, String password) throws ServletException {
        req.login(username, password);
    }

    public void logout() throws ServletException {
        req.logout();
    }

    public Collection<Part> getParts() throws IOException, ServletException {
        return req.getParts();
    }

    public Part getPart(String name) throws IOException, ServletException {
        return req.getPart(name);
    }

//    public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
//        return req.upgrade(handlerClass);
//    }

    public byte[] getBs() {
        return bs;
    }
}
