package com.dbr.generator.springboot.repository;

import com.dbr.generator.GeneratorProjectMetaData;
import com.dbr.generator.basic.entity.ProcessEntity;
import com.dbr.generator.springboot.app.mapping.ProcessEntityProcessDTOMapping;
import com.dbr.generator.springboot.app.repository.ProcessJPARepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneratorProcessEntitySaveTest {

    @Autowired
    private ProcessEntityProcessDTOMapping processEntityProcessDTOMapping;

    @Autowired
    private ProcessJPARepository processJPARepository;

    @Test
    public void saveProcess() {
        ProcessEntity processEntity = processJPARepository.save(processEntityProcessDTOMapping.toEntity(GeneratorProjectMetaData.PROCESS_DTO));
        assertThat(processEntity.getId()).isNotNull();
    }

}