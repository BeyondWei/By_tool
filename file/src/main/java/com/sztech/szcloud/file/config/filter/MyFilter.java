package com.sztech.szcloud.file.config.filter;

import com.sztech.szcloud.common.properties.ResourceProperties;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Component
@Slf4j
@Order(2)
public class MyFilter implements Filter {

    @Autowired
    private ResourceProperties resourceProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String picUrl = resourceProperties.getPicUrl();
        String[] split = picUrl.split(":");
        String hostAddress = "http://" + address.getHostAddress() + ":" + split[2];
        resourceProperties.setPicUrl(hostAddress);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("MyFilter");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
