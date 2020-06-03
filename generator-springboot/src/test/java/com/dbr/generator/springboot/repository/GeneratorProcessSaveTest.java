package com.dbr.generator.springboot.repository;

import com.dbr.generator.GeneratorProjectMetaData;
import com.dbr.generator.basic.entity.Process;
import com.dbr.generator.springboot.app.mapping.ProcessProcessDTOMapping;
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
public class GeneratorProcessSaveTest {

    @Autowired
    private ProcessProcessDTOMapping processProcessDTOMapping;

    @Autowired
    private ProcessJPARepository processJPARepository;

    @Test
    public void saveProcess() {
        Process process = processJPARepository.save(processProcessDTOMapping.toEntity(GeneratorProjectMetaData.PROCESS_DTO));
        assertThat(process.getId()).isNotNull();
    }

}