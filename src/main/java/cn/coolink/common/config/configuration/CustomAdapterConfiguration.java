package cn.coolink.common.config.configuration;


import cn.coolink.interceptor.JwtAuthenticateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Project Name:
 * Package Name:
 * Function: 自定义适配器
 * user:
 * Date: 2018/3/8
 */
@EnableWebMvc
@Configuration
public class CustomAdapterConfiguration  extends WebMvcConfigurerAdapter {

    /*@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RequestJsonParamMethodArgumentResolver());
    }*/


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/").addResourceLocations("*//**//**");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        super.addResourceHandlers(registry);
    }

    @Bean
    public JwtAuthenticateInterceptor jwtAuthenticateInterceptor(){
        return new JwtAuthenticateInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
//        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
        registry.addInterceptor(jwtAuthenticateInterceptor()).addPathPatterns("/doLogout/*","/org/*","/role/*","/area/*","/dict/*","/function/*","/member/*","/warehouse/*","/bu/**");
        super.addInterceptors(registry);
    }

}
