package com.dbr.generator.springboot.system.listener;

import com.dbr.generator.springboot.GeneratorProjectMetaData;
import com.dbr.generator.springboot.app.mapping.ProcessDTOProcessModelMapping;
import com.dbr.generator.springboot.app.mapping.ProcessEntityProcessDTOMapping;
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
    private final ProcessEntityProcessDTOMapping processEntityProcessDTOMapping;
    private final ProcessDTOProcessModelMapping processDTOProcessModelMapping;

    public InitialDataLoader(ApplicationProperties applicationProperties, ProcessJPARepository processJPARepository, ProcessEntityProcessDTOMapping processEntityProcessDTOMapping, ProcessDTOProcessModelMapping processDTOProcessModelMapping) {
        this.applicationProperties = applicationProperties;
        this.processJPARepository = processJPARepository;
        this.processEntityProcessDTOMapping = processEntityProcessDTOMapping;
        this.processDTOProcessModelMapping = processDTOProcessModelMapping;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (applicationProperties.getBuildEnvironments().contains(BuildEnvironment.DEV)) {
            processJPARepository.save(processEntityProcessDTOMapping.toEntity(processDTOProcessModelMapping.toDTO(GeneratorProjectMetaData.PROCESS_MODEL)));
        }
    }

}
