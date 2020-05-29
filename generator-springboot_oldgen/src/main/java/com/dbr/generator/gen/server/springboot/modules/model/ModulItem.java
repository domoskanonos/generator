package com.dbr.generator.gen.server.springboot.modules.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModulItem {
    private String packageSuffix;
    private String templateName;
    private String filename;
}
