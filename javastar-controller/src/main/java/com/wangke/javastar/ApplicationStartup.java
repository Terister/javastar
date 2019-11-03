package com.wangke.javastar;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by wolf
 */
public class ApplicationStartup implements ApplicationListener<ApplicationPreparedEvent> {

    private String LOTUS_ENV_KEY="Lotus-Env";

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        System.setProperty("APP_ENV", getEnv());
    }

    private String getEnv() {
       //从服务器环境中读取对应配置信息
        return "dev";
    }
}
