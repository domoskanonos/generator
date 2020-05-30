package com.dbr.generator.basic.project;

import com.dbr.generator.basic.project.dto.NidocaProjectDTO;
import com.dbr.generator.basic.project.dto.ProjectDTO;
import com.dbr.generator.basic.project.dto.SpringBootProjectDTO;
import com.dbr.generator.basic.project.generator.NidocaProjectGenerator;
import com.dbr.generator.basic.project.generator.ProjectGenerator;
import com.dbr.generator.basic.project.generator.SpringBootProjectGenerator;

public class ProjectFactory {

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
