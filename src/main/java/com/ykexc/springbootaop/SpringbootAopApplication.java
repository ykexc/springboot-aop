package com.ykexc.springbootaop;

import com.ykexc.springbootaop.config.BeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Random;

@SpringBootApplication
public class SpringbootAopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootAopApplication.class, args);
        BeanConfig beanConfig = context.getBean(BeanConfig.class);
        System.out.println(context.getBean(Random.class));
        System.out.println(beanConfig);
    }

}
