package com.dbr.generator.springboot.util;

import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.dto.MappingClazzItemMergerDTO;
import com.dbr.generator.basic.process.ProcessGenerator;
import com.dbr.generator.basic.process.dto.ProcessDTO;
import com.dbr.generator.basic.project.dto.JavaProjectDTO;
import com.dbr.generator.basic.project.dto.SpringBootProjectDTO;
import com.dbr.generator.basic.property.dto.PropertyDTO;
import com.dbr.generator.springboot.entity.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MainGenerator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws Exception {

        String processParentPath = new File("C:\\_dev\\vhs").getAbsolutePath();
        String processTempPath = new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath();
        ProcessDTO processDTO = new ProcessDTO(processTempPath, processParentPath, "generator");

        SpringBootProjectDTO springBootProjectDTO = new SpringBootProjectDTO(processDTO, "springboot", "com.dbr.generator");
        springBootProjectDTO.setAddSpringBootSecurityModule(true);
        //processDTO.getProjectDTOS().add(springBootProjectDTO);


        JavaProjectDTO javaProjectDTO = new JavaProjectDTO(processDTO, "springboot", "com.dbr.generator");
        ItemDTO itemDTOItemDTO = new JavaClass2ItemDTOConverter().convert(springBootProjectDTO.getProjectFolder().getAbsolutePath(), ItemDTO.class);
        itemDTOItemDTO.setJavaClazzName(new StringBuilder().append(springBootProjectDTO.getJavaDTOPackageName()).append(".ItemDTO").toString());
        ItemDTO itemDTOItemEntity = new JavaClass2ItemDTOConverter().convert(springBootProjectDTO.getProjectFolder().getAbsolutePath(), ItemDTO.class);
        itemDTOItemEntity.setJavaClazzName(new StringBuilder().append(springBootProjectDTO.getJavaEntityPackageName()).append(".Item").toString());

        ItemDTO itemDTOMappingClazz1 = new JavaClass2ItemDTOConverter().convert(springBootProjectDTO.getProjectFolder().getAbsolutePath(), Property.class);
        ItemDTO itemDTOMappingClazz2 = new JavaClass2ItemDTOConverter().convert(springBootProjectDTO.getProjectFolder().getAbsolutePath(), PropertyDTO.class);
        //itemDTOItemEntity.setJavaClazzName(new StringBuilder().append(springBootProjectDTO.getJavaEntityPackageName()).append(".Item").toString());


        //javaProjectDTO.getItemMergerDTOS().add(new EntityItemMergerDTO(itemDTOItemEntity));
        //javaProjectDTO.getItemMergerDTOS().add(new DTOItemMergerDTO(itemDTOItemDTO));
        javaProjectDTO.getItemDTOS().add(new MappingClazzItemMergerDTO(itemDTOMappingClazz1, itemDTOMappingClazz2));
        processDTO.getProjectDTOS().add(javaProjectDTO);


        //NidocaProjectDTO nidocaProjectDTO = new NidocaProjectDTO(processDTO, "nidoca");
        //processDTO.getProjectDTOS().add(nidocaProjectDTO);

        ProcessGenerator.generate(processDTO);

    }





}
