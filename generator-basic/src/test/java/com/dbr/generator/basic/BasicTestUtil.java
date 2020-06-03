package com.dbr.generator.basic;

import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.ProcessDTO;
import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.dto.PropertyDTO;

import java.io.File;

public class BasicTestUtil {

    public static ProcessDTO processDTO;
    public static ProjectDTO projectDTO = new ProjectDTO(processDTO, "project");

    public static ItemDTO clazzMappingItemDTO;
    public static ItemDTO subitemDTO1;
    public static ItemDTO subitemDTO2;

    public static ItemDTO subitemTypescript;

    static {
        String tempDirPath = new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath();


    }

    static {
    }
}
