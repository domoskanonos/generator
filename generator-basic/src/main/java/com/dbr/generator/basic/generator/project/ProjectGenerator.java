package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.basic.merger.ItemTemplateMerger;
import com.dbr.generator.basic.merger.TemplateEnum;

public class ProjectGenerator<T extends ProjectModel> {

    public void execute(T model) throws Exception {
        for (ItemModel itemModel : model.getItems()) {
            for (TemplateEnum templateEnum : itemModel.getTemplate()) {
                new ItemTemplateMerger().writeDown(model.getProjectFolder(), templateEnum, itemModel);
            }
        }
    }

    public void validate(T model) {

    }
}
