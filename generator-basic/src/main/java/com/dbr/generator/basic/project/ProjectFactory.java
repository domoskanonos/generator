package com.dbr.generator.basic.project;

import com.dbr.generator.basic.project.generator.NidocaProjectGenerator;
import com.dbr.generator.basic.project.dto.NidocaProjectDTO;
import com.dbr.generator.basic.project.dto.ProjectDTO;

import java.io.IOException;

public class ProjectFactory {

    public static ProjectGeneratorInterface create(ProjectDTO projectDTO) throws IOException {
        if (projectDTO instanceof NidocaProjectDTO) {
            new NidocaProjectGenerator().execute((NidocaProjectDTO) projectDTO);
        }
        return null;
    }

}
