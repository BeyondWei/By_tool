package com.sztech.szcloud.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置参数映射
 */

@Data
@Component
@ConfigurationProperties(prefix = "file.resource")
public class ResourceProperties {

    private String exclePath;
    private String wordPath;
    private String pdfPath;
    private String picPath;
    private String picUrl;

}
