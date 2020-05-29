package com.dbr.generator.basic.item.converter.dto;

import com.dbr.generator.basic.project.dto.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemConverterDTO {

    private ProjectDTO projectDTO;
    private String javaPackageName;
    private Class<?> clazz;

}
