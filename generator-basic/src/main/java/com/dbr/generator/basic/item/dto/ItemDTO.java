package com.dbr.generator.basic.item.dto;

import com.dbr.generator.basic.dto.ProjectDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.item.generatoraction.ItemGeneratorActionDTO;
import com.dbr.util.StringUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDTO {

    private ProjectDTO projectDTO;
    private String javaIdClazzSimpleName;
    private String javaClazzName;
    private String javaClazzSimpleName;
    private List<PropertyDTO> properties = new ArrayList<>();
    private List<ItemGeneratorActionDTO> itemGeneratorActionDTOS = new ArrayList<>();

    public Boolean useJPAIdClazz() {
        return this.javaIdClazzSimpleName != null;
    }

    public String getTableName() {
        return StringUtil.toDatabaseName(this.javaClazzSimpleName);
    }

    public String getTypescriptModelName() {
        return new StringBuilder().append(javaClazzSimpleName).append("Model").toString();
    }

    public String getTypescriptModelFilename() {
        return String.format("%s.ts", getTypescriptModelFilename().toLowerCase());
    }

}
