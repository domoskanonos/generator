package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.model.project.NidocaProjectModel;
import com.dbr.generator.basic.util.GeneratorUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class NidocaProjectGenerator extends ProjectGenerator<NidocaProjectModel> {

    @Override
    public void execute(NidocaProjectModel model) throws Exception {
        GeneratorUtil.deleteFile(model.getNidocaProjectFolder());
        File nidocaTemplateZipFile = GeneratorUtil.copyUrlToTempFolder(model.getNidocaTemplateZipUrl(), model.getNidocaTemplateZipFile());
        GeneratorUtil.unzipFile(nidocaTemplateZipFile, model.getProcessTempFolder());
        FileUtils.moveDirectory(new File(model.getProcessTempFolder(), model.getNidocaTemplateFilename()), model.getNidocaProjectFolder());
        super.execute(model);
    }

    @Override
    public void validate(NidocaProjectModel model) {
        super.validate(model);
    }
}
