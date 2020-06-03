package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.merger.ItemTemplateMerger;
import com.dbr.generator.basic.merger.TemplateEnum;

public class ProjectGenerator<T extends ProjectDTO> {

    public void execute(T model) throws Exception {
        for (ItemDTO itemDTO : model.getItems()) {
            for (TemplateEnum templateEnum : itemDTO.getTemplate()) {
                new ItemTemplateMerger().writeDown(model.getProjectFolder(), templateEnum, itemDTO);
            }
        }
    }

    public void validate(T model) {

    }
}
