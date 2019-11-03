package com.wangke.javastar.job;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by admin on 2017/11/8.
 */
@SpringBootApplication
@Import(SpringWebMvcConfig.class)
//@EnableMybatis
@ComponentScan("com.wangke.javastar")
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }

}
