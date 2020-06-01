package com.dbr.generator.basic;

import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.MergerTemplates;
import com.dbr.generator.basic.process.dto.ProcessDTO;
import com.dbr.generator.basic.project.dto.ProjectDTO;
import com.dbr.generator.basic.property.dto.PropertyDTO;

import java.io.File;

public class BasicTestUtil {

    public static ProcessDTO processDTO;
    public static ProjectDTO projectDTO = new ProjectDTO(processDTO, "project");

    public static ItemDTO clazzMappingItemDTO;
    public static ItemDTO subitemDTO1;
    public static ItemDTO subitemDTO2;

    static {
        String tempDirPath = new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath();
        processDTO = new ProcessDTO(tempDirPath, tempDirPath, "process");


        subitemDTO1 = new JavaClass2ItemDTOConverter().convert("", MergerTemplates.DTO_TEMPLATE, PropertyDTO.class);
        subitemDTO2 = new JavaClass2ItemDTOConverter().convert("", MergerTemplates.DTO_TEMPLATE, ItemDTO.class);

        clazzMappingItemDTO = new JavaClass2ItemDTOConverter().convert("", MergerTemplates.CLAZZ_MAPPING_TEMPLATE, ItemDTO.class);

        clazzMappingItemDTO.getReferencedItems().add(subitemDTO1);
        clazzMappingItemDTO.getReferencedItems().add(subitemDTO2);

        projectDTO.getItemDTOS().add(clazzMappingItemDTO);

    }

    static {
    }
}
