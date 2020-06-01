package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.dto.project.NidocaProjectDTO;
import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.dto.project.SpringBootProjectDTO;

public class ProjectGeneratorFactory {

    public static ProjectGeneratorInterface create(ProjectDTO projectDTO) {
        if (projectDTO instanceof SpringBootProjectDTO) {
            return new SpringBootProjectGenerator();
        }
        if (projectDTO instanceof NidocaProjectDTO) {
            return new NidocaProjectGenerator();
        }
        return new ProjectGenerator();
    }

}
