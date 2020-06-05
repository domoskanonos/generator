package com.dbr.generator.basic.model.project;

import com.dbr.generator.basic.enumeration.TemplateEnum;
import com.dbr.generator.basic.model.ProcessModel;
import lombok.Data;

@Data
public class JavaProjectModel extends ProjectModel {

    private String springBootArchetypeArtifactId = "springboottemplate-archetype";
    private String springBootGroupId = "com.dbr.springboot.template";

    public JavaProjectModel(ProcessModel processModel, String technicalDescriptor, String javaBasePackage, TemplateEnum... projectTemplates) {
        super(processModel, technicalDescriptor,projectTemplates);
        this.namespase = javaBasePackage;
    }

    public String getProjectGroupId() {
        return getNamespase();
    }

    public String getJavaEntityPackageName() {
        return new StringBuilder().append(getNamespase()).append(".entity").toString();
    }

    public String getJavaDTOPackageName() {
        return new StringBuilder().append(getNamespase()).append(".dto").toString();
    }

}
