package com.sztech.szcloud.common.config;

import com.github.pagehelper.PageHelper;
import com.sztech.szcloud.common.handle.CustomResponseErrorHandler;
import org.apache.catalina.connector.Connector;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;


/**
 * @Company 杭州数政科技有限公司
 * @Author 有成
 * @Date 2019-04-12 09:04
 * @Description spring实例化bean配置
 */
@Configuration
public class BeanConfig {

    final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    final HttpClient httpClient = HttpClientBuilder.create()
            .setRedirectStrategy(new LaxRedirectStrategy())
            .build();


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    /**
     * 配置线程池
     *
     * @return
     */
    @Bean(name = "polyThreadPool")
    @Qualifier(value = "polyThreadPool")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize((Runtime.getRuntime().availableProcessors() * 5 + 1));
        threadPoolExecutor.setMaxPoolSize((Runtime.getRuntime().availableProcessors() * 6 + 1));
        threadPoolExecutor.setKeepAliveSeconds(60 * 60 * 8);
        threadPoolExecutor.setBeanName("polyThreadPool");
        threadPoolExecutor.setAllowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    /**
     * 请求的特殊字符
     *
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setProperty("relaxedQueryChars", "|{}[]");
            }
        });
        return factory;
    }

    @Bean(name = "pageHelper")
    public PageHelper pageHelper(){
        return new PageHelper();
    }



}
