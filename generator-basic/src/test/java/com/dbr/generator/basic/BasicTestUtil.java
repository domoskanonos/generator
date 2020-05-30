package com.dbr.generator.basic;

import com.dbr.generator.basic.process.dto.ProcessDTO;
import com.dbr.generator.basic.project.dto.ProjectDTO;

import java.io.File;

public class BasicTestUtil {
    public static ProcessDTO processDTO;

    static {
        String tempDirPath = new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath();
        processDTO = new ProcessDTO(tempDirPath, tempDirPath, "process");
    }

    public static ProjectDTO projectDTO = new ProjectDTO(processDTO, "project");

    static {
        ;

        //"com.dbr.generator"
    }
}
