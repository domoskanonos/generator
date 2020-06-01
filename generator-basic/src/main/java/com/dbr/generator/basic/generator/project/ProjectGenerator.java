package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.merger.ItemTemplateMerger;
import com.dbr.generator.basic.dto.project.ProjectDTO;

public class ProjectGenerator implements ProjectGeneratorInterface<ProjectDTO> {
    @Override
    public void execute(ProjectDTO model) throws Exception {
        for (ItemDTO itemDTO : model.getItemDTOS()) {
            new ItemTemplateMerger(itemDTO).writeDown();
        }
    }

    @Override
    public void validate(ProjectDTO model) {

    }
}
