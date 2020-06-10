package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.merger.TemplateMerger;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.project.ProjectModel;

import java.io.File;

public class ProjectGenerator<T extends ProjectModel> {

    private TemplateMerger templateMerger = new TemplateMerger();

    public void execute(T model) throws Exception {
        for (Template template : model.getTemplate()) {
            templateMerger.writeDown(model.getFilePath(template), template, model);
        }

        for (ItemModel itemModel : model.getItems()) {
            for (Template template : itemModel.getTemplate()) {
                File file = itemModel.getFilePath(model.getProjectFolder(), template);
                templateMerger.writeDown(file, template, itemModel);
            }
        }
    }

    public void validate(T model) {

    }
}
