package com.dbr.generator.basic;

import com.dbr.generator.basic.project.dto.ProjectDTO;

import java.io.File;

public class BaseTestUtil {
    public static ProjectDTO projectDTO = new ProjectDTO();

    static {
        projectDTO.setJavaBasePackage("com.dbr.generator.test");
        projectDTO.setRootFolder(new File(""));
    }
}
