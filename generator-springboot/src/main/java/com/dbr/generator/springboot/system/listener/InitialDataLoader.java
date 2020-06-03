package com.dbr.generator.springboot.system.listener;

import com.dbr.generator.GeneratorProjectMetaData;
import com.dbr.generator.springboot.app.mapping.ProcessProcessDTOMapping;
import com.dbr.generator.springboot.app.repository.ProcessJPARepository;
import com.dbr.generator.springboot.system.ApplicationProperties;
import com.dbr.generator.springboot.system.enumeration.BuildEnvironment;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final ApplicationProperties applicationProperties;
    private final ProcessJPARepository processJPARepository;
    private final ProcessProcessDTOMapping processProcessDTOMapping;

    public InitialDataLoader(ApplicationProperties applicationProperties, ProcessJPARepository processJPARepository, ProcessProcessDTOMapping processProcessDTOMapping) {
        this.applicationProperties = applicationProperties;
        this.processJPARepository = processJPARepository;
        this.processProcessDTOMapping = processProcessDTOMapping;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (applicationProperties.getBuildEnvironments().contains(BuildEnvironment.DEV)) {
            processJPARepository.save(processProcessDTOMapping.toEntity(GeneratorProjectMetaData.PROCESS_DTO));
        }
    }

}
