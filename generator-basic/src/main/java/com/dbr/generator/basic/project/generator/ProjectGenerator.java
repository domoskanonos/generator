package com.dbr.generator.basic.project.generator;

import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.ItemMerger;
import com.dbr.generator.basic.project.ProjectGeneratorInterface;
import com.dbr.generator.basic.project.dto.ProjectDTO;

public class ProjectGenerator implements ProjectGeneratorInterface<ProjectDTO> {
    @Override
    public void execute(ProjectDTO model) throws Exception {
        for (ItemDTO itemDTO : model.getItemDTOS()) {
            new ItemMerger(itemDTO).writeDown();
        }
    }

    @Override
    public void validate(ProjectDTO model) {

    }
}
