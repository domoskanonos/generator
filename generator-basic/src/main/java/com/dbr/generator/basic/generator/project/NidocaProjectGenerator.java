package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.merger.ItemTemplateTemplateMerger;
import com.dbr.generator.basic.dto.project.NidocaProjectDTO;
import com.dbr.generator.basic.util.GeneratorUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class NidocaProjectGenerator implements ProjectGeneratorInterface<NidocaProjectDTO> {

    @Override
    public void execute(NidocaProjectDTO model) throws IOException {
        GeneratorUtil.deleteFile(model.getNidocaProjectFolder());
        File nidocaTemplateZipFile = GeneratorUtil.copyUrlToTempFolder(model.getNidocaTemplateZipUrl(),
                model.getNidocaTemplateZipFile());
        GeneratorUtil.unzipFile(nidocaTemplateZipFile, model.getProcessFolder());
        FileUtils.moveDirectory(new File(model.getProcessFolder(), model.getNidocaTemplateFilename()), model.getNidocaProjectFolder());
        for (ItemDTO itemDTO : model.getItemDTOS()) {
            new ItemTemplateTemplateMerger(itemDTO).writeDown();
        }
    }

    @Override
    public void validate(NidocaProjectDTO model) {

    }
}
