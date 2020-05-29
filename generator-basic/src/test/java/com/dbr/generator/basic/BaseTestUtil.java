package com.dbr.generator.basic;

import com.dbr.generator.basic.dto.ProjectDTO;

public class BaseTestUtil {
    public static ProjectDTO projectDTO = new ProjectDTO();

    static {
        projectDTO.setJavaBasePackage("com.dbr.generator.test");
    }
}
