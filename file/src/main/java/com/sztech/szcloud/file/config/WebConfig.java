package com.sztech.szcloud.file.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.sztech.szcloud.common.handle.ResultBeanReturnValueHandler;
import com.sztech.szcloud.common.properties.ResourceProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ResourceProperties resourceProperties;

    @Autowired
    private ResultBeanReturnValueHandler resultBeanReturnValueHandler;


    /**
     * 将 userIconPath 映射为静态资源目录
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + resourceProperties.getPicPath());
    }


    /**
     * 添加返回值处理程序
     * @param handlers
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(resultBeanReturnValueHandler);
    }

    /**
     * 配置消息转换器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        /*
         * 1、需要先定义一个convert转换消息的对象 2、添加fastJson的配置信息，比如：是否要格式化返回json数据 3、在convert中添加配置信息
         * 4、将convert添加到converters当中
         *
         */
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 自定义转换器默认插入在最后一个，所以需要改变一下他的位子才能生效
        converters.add(0,fastConverter);
    }

}
