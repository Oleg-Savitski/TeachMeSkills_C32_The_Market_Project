package com.teachmeskills.market.utils.config.application;

import com.teachmeskills.market.annotation.LeadTimed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan("com.teachmeskills.market")
@Configuration
public class AppConfig {

    @LeadTimed("-> Worked out method viewResolver")
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}