package com.ykexc.springbootaop.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mqz
 */
@ConfigurationProperties(prefix = "xx")
@Data
public class BeanConfig {


    String BeanName = "bean";


    String Y = "y";


}
