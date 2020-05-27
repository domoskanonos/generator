package com.dbr.generator.main.model;

import lombok.Data;

import java.io.File;

@Data
public class MainGeneratorModel {

    private File rootFolder;

    private String technicalDescriptor;

    public File getProjectFolder() {
        return new File(rootFolder, technicalDescriptor);
    }
}
