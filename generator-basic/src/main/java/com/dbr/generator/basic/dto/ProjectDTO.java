package com.dbr.generator.basic.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectDTO {
    private String javaPackageName = "";
    private List<ObjectDTO> objects = new ArrayList<>();
}
