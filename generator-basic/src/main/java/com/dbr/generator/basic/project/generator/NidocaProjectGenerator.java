package com.dbr.generator.basic.project.generator;

import com.dbr.generator.basic.item.merger.ItemMergerFactory;
import com.dbr.generator.basic.item.merger.dto.ItemMergerDTO;
import com.dbr.generator.basic.project.ProjectGeneratorInterface;
import com.dbr.generator.basic.project.dto.NidocaProjectDTO;
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
        for (ItemMergerDTO itemMergerDTO : model.getItemMergerDTOS()) {
            ItemMergerFactory.createMerger(itemMergerDTO).writeDown();
        }
    }

    @Override
    public void validate(NidocaProjectDTO model) {

    }
}
