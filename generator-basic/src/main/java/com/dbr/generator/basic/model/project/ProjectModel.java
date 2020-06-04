package com.dbr.generator.basic.model.project;

import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.ProcessModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProjectModel {

    private String technicalDescriptor;

    protected String javaBasePackage;

    private ProcessModel processModel;

    private List<ItemModel> items = new ArrayList<>();

    public ProjectModel(ProcessModel processModel, String technicalDescriptor) {
        this.processModel = processModel;
        this.technicalDescriptor = technicalDescriptor;
    }

    public File getProjectFolder() {
        return new File(processModel.getProcessFolder(), new StringBuilder().append(processModel.getProjectFolderPrefix()).append(technicalDescriptor).toString());
    }

}
