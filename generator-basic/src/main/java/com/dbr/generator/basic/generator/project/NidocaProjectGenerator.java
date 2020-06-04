package com.dbr.generator.basic.generator.project;

import com.dbr.generator.basic.model.project.NidocaProjectModel;

public class NidocaProjectGenerator extends ProjectGenerator<NidocaProjectModel> {

    @Override
    public void execute(NidocaProjectModel model) throws Exception {
        //GeneratorUtil.deleteFile(model.getNidocaProjectFolder());
        //File nidocaTemplateZipFile = GeneratorUtil.copyUrlToTempFolder(model.getNidocaTemplateZipUrl(), model.getNidocaTemplateZipFile());
        //GeneratorUtil.unzipFile(nidocaTemplateZipFile, model.getProcessFolder());
        //FileUtils.moveDirectory(new File(model.getProcessFolder(), model.getNidocaTemplateFilename()), model.getNidocaProjectFolder());
        super.execute(model);
    }

    @Override
    public void validate(NidocaProjectModel model) {
        super.validate(model);
    }
}
