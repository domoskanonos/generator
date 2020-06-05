package com.dbr.generator.basic.model.project;

import com.dbr.generator.basic.enumeration.TemplateEnum;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.ProcessModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.*;

@Data
@NoArgsConstructor
public class ProjectModel {

    private String technicalDescriptor;

    protected String namespase;

    private ProcessModel processModel;

    private List<ItemModel> items = new ArrayList<>();

    private Set<TemplateEnum> template = new HashSet<>();

    public ProjectModel(ProcessModel processModel, String technicalDescriptor, TemplateEnum... projectTemplates) {
        this.processModel = processModel;
        this.technicalDescriptor = technicalDescriptor;
        this.template = new HashSet<>(Arrays.asList(projectTemplates));
    }

    public File getProjectFolder() {
        return new File(processModel.getProcessFolder(), new StringBuilder().append(processModel.getProjectFolderPrefix()).append(technicalDescriptor).toString());
    }

    public File getFilePath(TemplateEnum projectTemplateEnum) {
        return new File(getProjectFolder(), getFileSuffix(projectTemplateEnum));
    }

    private String getFileSuffix(TemplateEnum templateEnum) {
        switch (templateEnum) {
            case PROJECT_TYPESCRIPT_NIDOCA_PAGE_SERVICE:
                return templateEnum.getProjectFilePath("");
            default:
                throw new RuntimeException("error determinate file path...");
        }

    }
}
