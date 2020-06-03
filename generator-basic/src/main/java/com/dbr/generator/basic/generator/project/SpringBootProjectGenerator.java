package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.project.SpringBootProjectDTO;
import com.dbr.generator.basic.merger.ItemTemplateMerger;
import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.generator.basic.util.ValidationUtil;

import java.io.File;
import java.io.IOException;

public class SpringBootProjectGenerator extends ProjectGenerator<SpringBootProjectDTO> {

    @Override
    public void execute(SpringBootProjectDTO model) throws Exception {
        GeneratorUtil.deleteFile(model.getSpringBootProjectFolder());
        File springBootZipFile = GeneratorUtil.copyUrlToTempFolder(model.getSpringBootTemplateZipUrl(),
                model.getSpringBootTemplateZipFile());
        GeneratorUtil.unzipFile(springBootZipFile, model.getProcessTempFolder());
        GeneratorUtil.createMavenArchetype(model.getSpringBootTemplateFolder());
        GeneratorUtil.createFromArchetype(model.getProcessFolder(), model.getSpringBootProjectArtifactId(),
                model.getProjectGroupId(), model.getSpringBootArchetypeArtifactId(),
                model.getSpringBootGroupId());

        if (!model.getAddSpringBootMailRestController()) {
            GeneratorUtil.deleteFile(new File(model.getSpringBootProjectSourceBasePackageFolder(), "system/mail/rest/MailRestController.java"));
        }

        if (!model.getAddSpringBootSecurityModule()) {
            GeneratorUtil.deleteFile(new File(model.getSpringBootProjectSourceBasePackageFolder(), "system/auth"));
            GeneratorUtil.deleteFile(new File(model.getSpringBootProjectResourceFolder(), "public/login.html"));
            GeneratorUtil.deleteFile(new File(model.getSpringBootProjectResourceFolder(), "application-disable-security.properties"));
        }

        if (!model.getAddSpringBootStorageModule()) {
            GeneratorUtil.deleteFile(new File(model.getSpringBootProjectSourceBasePackageFolder(), "system/storage"));
            GeneratorUtil.deleteFile(new File(model.getSpringBootProjectTestSourceBasePackageFolder(), "system/storage"));
            GeneratorUtil.deleteFile(new File(model.getSpringBootProjectResourceFolder(), "upload.properties"));
        }
        super.execute(model);
    }

    @Override
    public void validate(SpringBootProjectDTO model) {
        super.validate(model);
        ValidationUtil.validateNotHDDBaseDirectory(model.getSpringBootProjectFolder());
    }

}
