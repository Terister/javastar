package com.wangke.javastar.job;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by admin on 2017/11/8.
 */
@SpringBootApplication
@Import(SpringWebMvcConfig.class)
//@EnableMybatis
@ComponentScan("com.wangke.javastar")
@EnableScheduling
public class SpringApplication {
    public static void main(String[] args) {
            new SpringApplicationBuilder(SpringApplication.class).web(true).run(args);
    }

}
