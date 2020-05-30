package com.dbr.generator.basic.project.generator;

import com.dbr.generator.basic.item.merger.ItemMergerFactory;
import com.dbr.generator.basic.item.merger.dto.ItemMergerDTO;
import com.dbr.generator.basic.project.ProjectGeneratorInterface;
import com.dbr.generator.basic.project.dto.ProjectDTO;

public class ProjectGenerator implements ProjectGeneratorInterface<ProjectDTO> {
    @Override
    public void execute(ProjectDTO model) throws Exception {
        for (ItemMergerDTO itemMergerDTO : model.getItemMergerDTOS()) {
            ItemMergerFactory.createMerger(itemMergerDTO).writeDown();
        }
    }

    @Override
    public void validate(ProjectDTO model) {

    }
}
