package com.wangke.javastar.job;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

/**
 * @author wang.zeyan
 * @date 2017年10月25日
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ServletComponentScan("com.microseer.databaseinfo")
public class SpringWebMvcConfig extends WebMvcConfigurerAdapter {

    public static final String platform_server_port = "server.port";
    public static final String platform_server_contextPath = "server.contextPath";
    public static final String default_server_port = "8080";
    public static final String default_server_contextPath = "/";

    /**
     * undertow web容器注册
     *
     * @return
     */
//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
//        factory.setPort(7812);
////        if(StringUtils.hasText(PlatformProp.getProperty(platform_server_contextPath))
////        		&& !PlatformProp.getProperty(platform_server_contextPath).equals(default_server_contextPath)){
//       	factory.setContextPath("/");
////        }
//        return factory;
//    }

    /*  *
     * undertow web容器注册
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver factory = new InternalResourceViewResolver();

        factory.setPrefix("/WEB-INF/views/");
        factory.setSuffix(".jsp");
        return factory;
    }


    @Bean
    public FreeMarkerConfigurer viewResolver(ServletContext servletContext) {
        FreeMarkerConfigurer fm;
        fm = new FreeMarkerConfigurer();
        //  fm.setTemplateLoaderPath("classpath:templates/views/");
        fm.setTemplateLoaderPath("classpath:templates/views/");
        fm.setDefaultEncoding("UTF-8");
        return fm;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:templates/static/");
    }


//	@Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .useDefaultResponseMessages(false)
//        		.forCodeGeneration(true)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.microseer.databaseinfo.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("openApi 商户管理 RESTful APIs")
//                .description("更多API 请联系：wang.zeyan")
//                .termsOfServiceUrl("http://www.microseer.com/")
//                .license("上海微知软件科技有限公司")
//                .version("1.0")
//                .build();
//    }
}
