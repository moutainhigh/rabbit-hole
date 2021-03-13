package com.github;

import com.github.lotus.chaos.BootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackageClasses = BootApplication.class)
@EnableAutoConfiguration
public class TestConfiguration {
}
