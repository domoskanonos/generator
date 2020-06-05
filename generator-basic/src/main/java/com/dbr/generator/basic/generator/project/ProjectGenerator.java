package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.enumeration.TemplateEnum;
import com.dbr.generator.basic.merger.TemplateMerger;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.project.ProjectModel;

import java.io.File;

public class ProjectGenerator<T extends ProjectModel> {

    private TemplateMerger templateMerger = new TemplateMerger();

    public void execute(T model) throws Exception {
        for (TemplateEnum templateEnum : model.getTemplate()) {
            templateMerger.writeDown(model.getFilePath(templateEnum), templateEnum, model);
        }

        for (ItemModel itemModel : model.getItems()) {
            for (TemplateEnum templateEnum : itemModel.getTemplate()) {
                File file = itemModel.getFilePath(model.getProjectFolder(), templateEnum);
                templateMerger.writeDown(file, templateEnum, itemModel);
            }
        }
    }

    public void validate(T model) {

    }
}
