package com.dbr.generator.basic;

import com.dbr.generator.basic.process.dto.ProcessDTO;
import com.dbr.generator.basic.project.dto.ProjectDTO;

import java.io.File;

public class BasicTestUtil {
    public static ProjectDTO projectDTO = new ProjectDTO(new ProcessDTO(new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath(), new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath(), "HUhu"), "sdsd", "com.dbr.generator");

    static {
        projectDTO.setJavaBasePackage("com.dbr.generator.test");
    }
}
