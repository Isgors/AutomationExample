package com.automation;

import com.automation.web.BrowserDriverConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AutomationConfiguration {

    @Bean
    public BrowserDriverConfiguration browserDriverConfiguration (){
        return new BrowserDriverConfiguration();
    }

}
