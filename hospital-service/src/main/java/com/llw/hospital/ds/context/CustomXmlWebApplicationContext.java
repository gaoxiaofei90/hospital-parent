package com.llw.hospital.ds.context;


import org.springframework.web.context.support.XmlWebApplicationContext;

public class CustomXmlWebApplicationContext extends XmlWebApplicationContext {
    @Override
    protected void onRefresh() {
        super.onRefresh();
    }
}
