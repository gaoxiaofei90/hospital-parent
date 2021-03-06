package com.llw.hospital.bs.common.util;

/**
 * see org.springframework.cloud.netflix.zuul.filters.support.FilterConstants
 * Created by liaowu on 2017/2/21.
 */
public class FilterConstants{
    public static final String IS_DISPATCHER_SERVLET_REQUEST_KEY = "isDispatcherServletRequest";
    public static final String FORWARD_TO_KEY = "forward.to";
    public static final String PROXY_KEY = "proxy";
    public static final String REQUEST_ENTITY_KEY = "requestEntity";
    public static final String REQUEST_URI_KEY = "requestURI";
    public static final String RETRYABLE_KEY = "retryable";
    public static final String ROUTING_DEBUG_KEY = "routingDebug";
    public static final String SERVICE_ID_KEY = "serviceId";
    public static final int DEBUG_FILTER_ORDER = 1;
    public static final int FORM_BODY_WRAPPER_FILTER_ORDER = -1;
    public static final int PRE_DECORATION_FILTER_ORDER = 5;
    public static final int RIBBON_ROUTING_FILTER_ORDER = 10;
    public static final int SEND_ERROR_FILTER_ORDER = 0;
    public static final int SEND_FORWARD_FILTER_ORDER = 500;
    public static final int SEND_RESPONSE_FILTER_ORDER = 1000;
    public static final int SIMPLE_HOST_ROUTING_FILTER_ORDER = 100;
    public static final int SERVLET_30_WRAPPER_FILTER_ORDER = -2;
    public static final int SERVLET_DETECTION_FILTER_ORDER = -3;
    public static final String ERROR_TYPE = "error";
    public static final String POST_TYPE = "post";
    public static final String PRE_TYPE = "pre";
    public static final String ROUTE_TYPE = "route";
    public static final String FORWARD_LOCATION_PREFIX = "forward:";
    public static final int HTTP_PORT = 80;
    public static final int HTTPS_PORT = 443;
    public static final String HTTP_SCHEME = "http";
    public static final String HTTPS_SCHEME = "https";
    public static final String SERVICE_HEADER = "X-Zuul-Service";
    public static final String SERVICE_ID_HEADER = "X-Zuul-ServiceId";
    public static final String X_FORWARDED_FOR_HEADER = "X-Forwarded-For";
    public static final String X_FORWARDED_HOST_HEADER = "X-Forwarded-Host";
    public static final String X_FORWARDED_PREFIX_HEADER = "X-Forwarded-Prefix";
    public static final String X_FORWARDED_PORT_HEADER = "X-Forwarded-Port";
    public static final String X_FORWARDED_PROTO_HEADER = "X-Forwarded-Proto";
    public static final String X_ZUUL_DEBUG_HEADER = "X-Zuul-Debug-Header";

    //custom Strings
    public static final String X_REAL_IP = "X-Real-IP";
    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    public static final String X_REQUESTED_WITH = "X-Requested-With";

    public static final String CONTENT_TYPE = "Content-type";
    public static final String CONTENT_TYPE_JSON = "application/json";
}
