package com.dbr.generator.basic.model.project;

import com.dbr.generator.basic.model.ProcessModel;
import lombok.Data;

@Data
public class JavaProjectModel extends ProjectModel {

    private String springBootArchetypeArtifactId = "springboottemplate-archetype";
    private String springBootGroupId = "com.dbr.springboot.template";

    public JavaProjectModel(ProcessModel processModel, String technicalDescriptor, String javaBasePackage) {
        super(processModel, technicalDescriptor);
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
