package com.dbr.generator.basic;

import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.MergerTemplates;
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
        processDTO = new ProcessDTO(tempDirPath, tempDirPath, "process");


        subitemDTO1 = new JavaClass2ItemDTOConverter().convert("", MergerTemplates.DTO_TEMPLATE, PropertyDTO.class);
        subitemDTO2 = new JavaClass2ItemDTOConverter().convert("", MergerTemplates.DTO_TEMPLATE, ItemDTO.class);
        subitemTypescript = new JavaClass2ItemDTOConverter().convert("", MergerTemplates.TYPESCRIPT_MODEL_TEMPLATE, ItemDTO.class);

        clazzMappingItemDTO = new JavaClass2ItemDTOConverter().convert("", MergerTemplates.CLAZZ_MAPPING_TEMPLATE, ItemDTO.class);
        clazzMappingItemDTO.addItemDTO(subitemDTO1);
        clazzMappingItemDTO.addItemDTO(subitemDTO2);

        projectDTO.getItemDTOS().add(clazzMappingItemDTO);

    }

    static {
    }
}
