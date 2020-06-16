package com.dbr.generator.basic.model.project;

import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.ProcessModel;
import lombok.Data;

import java.io.File;
import java.util.*;

@Data
public class ProjectModel {

    private String technicalDescriptor;

    protected String namespace;

    private Boolean deactivated;

    private ProcessModel processModel;

    private List<ItemModel> items = new ArrayList<>();

    private Set<Template> template = new HashSet<>();

    public ProjectModel(ProcessModel processModel, String technicalDescriptor, Template... projectTemplates) {
        this.processModel = processModel;
        this.technicalDescriptor = technicalDescriptor;
        this.template = new HashSet<>(Arrays.asList(projectTemplates));
    }

    public File getProjectFolder() {
        return new File(processModel.getProcessFolder(), new StringBuilder().append(processModel.getProjectFolderPrefix()).append(technicalDescriptor).toString());
    }

    public File getFilePath(Template projectTemplate) {
        return new File(getProjectFolder(), projectTemplate.getProjectFilePath(""));
    }

    public void addItem(ItemModel item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }
}
