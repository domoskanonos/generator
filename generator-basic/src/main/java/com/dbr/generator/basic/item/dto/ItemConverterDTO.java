package com.dbr.generator.basic.item.dto;

import com.dbr.generator.basic.project.dto.ProjectDTO;
import com.dbr.generator.basic.item.dto.generatoraction.ItemGeneratorActionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class ItemConverterDTO {

    private ProjectDTO projectDTO;
    private Class<?> clazz;
    private List<ItemGeneratorActionDTO> itemGeneratorActionDTOS;

    public ItemConverterDTO(ProjectDTO projectDTO, Class<?> clazz) {
        this(projectDTO, clazz, new ArrayList<>());
    }
}
