package com.beatshadow.example.autowired.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/31 14:22
 */
@Configuration
@ComponentScan(basePackages = {"com.beatshadow.example.autowired.service"})
public class AppConfig {

}
