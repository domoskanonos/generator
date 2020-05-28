package com.dbr.generator.basic.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModelDTO {

    private String idClazzSimpleName;
    private String packageName;
    private String clazzName;
    private String clazzSimpleName;
    private List<PropertyDTO> properties = new ArrayList<>();
}
