package com.ykexc.springbootaop.config;

import com.ykexc.springbootaop.annotation.MyConditional;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @author mqz
 */
@Configuration
@EnableConfigurationProperties({BeanConfig.class})
public class MainConfig {



    @Bean
    @MyConditional(value = "ykexc", forceCondition = true)
    public Random random() {
        return new Random();
    }

}
