package com.github.lotus.chaos.basic;

import in.hocg.boot.web.autoconfiguration.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by hocgin on 2021/7/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@ConditionalOnMissingBean
public class WarmUpLazyBeanRunner implements ApplicationRunner {

    @Async
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ApplicationContext context = SpringContext.getApplicationContext();
        log.debug("Warm Up Bean Task Start [{}]", WarmUpLazyBeanRunner.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            try {
                context.getBeansOfType(context.getType(beanDefinitionName, true), true, true);
            } catch (Exception e) {
                log.warn("Warm Up Bean=[{}] Error: {}", beanDefinitionName, e);
            } finally {
                log.debug("Warm Up Bean=[{}]", beanDefinitionName);
            }
        }
        log.debug("Warm Up Bean Task End [{}]", WarmUpLazyBeanRunner.class);
    }
}
