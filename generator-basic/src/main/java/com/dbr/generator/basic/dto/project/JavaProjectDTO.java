package com.dbr.generator.basic.dto.project;

import com.dbr.generator.basic.dto.ProcessDTO;
import lombok.Data;

@Data
public class JavaProjectDTO extends ProjectDTO {

    private String springBootArchetypeArtifactId = "springboottemplate-archetype";
    private String springBootGroupId = "com.dbr.springboot.template";

    public JavaProjectDTO(ProcessDTO processDTO, String technicalDescriptor, String javaBasePackage) {
        super(processDTO, technicalDescriptor);
        this.javaBasePackage = javaBasePackage;
    }

    public String getProjectGroupId() {
        return getJavaBasePackage();
    }

    public String getJavaEntityPackageName() {
        return new StringBuilder().append(getJavaBasePackage()).append(".entity").toString();
    }

    public String getJavaDTOPackageName() {
        return new StringBuilder().append(getJavaBasePackage()).append(".dto").toString();
    }

}
