package com.dbr.generator.basic.project.generator;

import com.dbr.generator.basic.project.ProjectGeneratorInterface;
import com.dbr.generator.basic.project.dto.SpringBootProjectDTO;
import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.generator.basic.util.ValidationUtil;

import java.io.File;
import java.io.IOException;

public class SpringBootProjectGenerator implements ProjectGeneratorInterface<SpringBootProjectDTO> {

    @Override
    public void execute(SpringBootProjectDTO springBootProjectDTO) throws IOException, InterruptedException {
        GeneratorUtil.deleteFile(springBootProjectDTO.getSpringBootProjectFolder());
        File springBootZipFile = GeneratorUtil.copyUrlToTempFolder(springBootProjectDTO.getSpringBootTemplateZipUrl(),
                springBootProjectDTO.getSpringBootTemplateZipFile());
        GeneratorUtil.unzipFile(springBootZipFile, springBootProjectDTO.getProcessTempFolder());
        GeneratorUtil.createMavenArchetype(springBootProjectDTO.getSpringBootTemplateFolder());
        GeneratorUtil.createFromArchetype(springBootProjectDTO.getProcessFolder(), springBootProjectDTO.getSpringBootProjectArtifactId(),
                springBootProjectDTO.getProjectGroupId(), springBootProjectDTO.getSpringBootArchetypeArtifactId(),
                springBootProjectDTO.getSpringBootGroupId());

        if (!springBootProjectDTO.getAddSpringBootMailRestController()) {
            GeneratorUtil.deleteFile(new File(springBootProjectDTO.getSpringBootProjectSourceBasePackageFolder(), "system/mail/rest/MailRestController.java"));
        }

        if (!springBootProjectDTO.getAddSpringBootSecurityModule()) {
            GeneratorUtil.deleteFile(new File(springBootProjectDTO.getSpringBootProjectSourceBasePackageFolder(), "system/auth"));
            GeneratorUtil.deleteFile(new File(springBootProjectDTO.getSpringBootProjectResourceFolder(), "public/login.html"));
            GeneratorUtil.deleteFile(new File(springBootProjectDTO.getSpringBootProjectResourceFolder(), "application-disable-security.properties"));
        }

        if (!springBootProjectDTO.getAddSpringBootStorageModule()) {
            GeneratorUtil.deleteFile(new File(springBootProjectDTO.getSpringBootProjectSourceBasePackageFolder(), "system/storage"));
            GeneratorUtil.deleteFile(new File(springBootProjectDTO.getSpringBootProjectTestSourceBasePackageFolder(), "system/storage"));
            GeneratorUtil.deleteFile(new File(springBootProjectDTO.getSpringBootProjectResourceFolder(), "upload.properties"));
        }

    }

    @Override
    public void validate(SpringBootProjectDTO model) {


        ValidationUtil.validateNotHDDBaseDirectory(model.getSpringBootProjectFolder());


    }

}
