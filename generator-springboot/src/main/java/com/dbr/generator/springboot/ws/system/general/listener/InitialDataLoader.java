package com.dbr.generator.springboot.ws.system.general.listener;


import com.dbr.generator.springboot.ws.system.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    }

}
