package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.model.project.NidocaProjectModel;
import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.basic.model.project.SpringBootProjectModel;

public class ProjectGeneratorFactory {

    public static ProjectGenerator create(ProjectModel projectModel) {
        if (projectModel instanceof SpringBootProjectModel) {
            return new SpringBootProjectGenerator();
        }
        if (projectModel instanceof NidocaProjectModel) {
            return new NidocaProjectGenerator();
        }
        return new ProjectGenerator();
    }

}
