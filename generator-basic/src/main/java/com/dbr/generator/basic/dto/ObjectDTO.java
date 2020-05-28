package com.dbr.generator.basic.dto;

import com.dbr.util.StringUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ObjectDTO {

    private ProjectDTO projectDTO;
    private String idClazzSimpleName;
    private String clazzName;
    private String clazzSimpleName;
    private List<PropertyDTO> properties = new ArrayList<>();

    public Boolean useJPAIdClazz() {
        return this.idClazzSimpleName != null;
    }

    public String getTableName() {
        return StringUtil.toDatabaseName(this.clazzSimpleName);
    }

    public String getTypescriptModelName() {
        return new StringBuilder().append(clazzSimpleName).append("Model").toString();
    }

    public String getTypescriptModelFilename() {
        return String.format("%s.ts", getTypescriptModelFilename().toLowerCase());
    }

}
