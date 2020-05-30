package com.dbr.generator.basic.module.nidoca;

import com.dbr.generator.basic.module.ModuleGeneratorInterface;
import com.dbr.generator.basic.project.dto.NidocaProjectDTO;
import com.dbr.generator.basic.util.GeneratorUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class NidocaModuleGenerator implements ModuleGeneratorInterface<NidocaProjectDTO> {

    @Override
    public void execute(NidocaProjectDTO nidocaProjectDTO) throws IOException {
        GeneratorUtil.deleteFile(nidocaProjectDTO.getNidocaProjectFolder());
        File nidocaTemplateZipFile = GeneratorUtil.copyUrlToTempFolder(nidocaProjectDTO.getNidocaTemplateZipUrl(),
                nidocaProjectDTO.getNidocaTemplateZipFile());
        GeneratorUtil.unzipFile(nidocaTemplateZipFile, nidocaProjectDTO.getProcessFolder());
        FileUtils.moveDirectory(new File(nidocaProjectDTO.getProcessFolder(), nidocaProjectDTO.getNidocaTemplateFilename()), nidocaProjectDTO.getNidocaProjectFolder());
    }

    @Override
    public void validate(NidocaProjectDTO model) {

    }
}
