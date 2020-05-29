package com.dbr.generator.gen.server.springboot.modules.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceItem {
    private String moduleName;
    private String resourcePath;
}
