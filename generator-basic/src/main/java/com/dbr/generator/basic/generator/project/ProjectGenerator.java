package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.merger.ItemTemplateMerger;
import com.dbr.generator.basic.dto.project.ProjectDTO;

public class ProjectGenerator<T extends ProjectDTO> {

    public void execute(T model) throws Exception {
        for (ItemDTO itemDTO : model.getItems()) {
            new ItemTemplateMerger(itemDTO).writeDown();
        }
    }

    public void validate(T model) {

    }
}
