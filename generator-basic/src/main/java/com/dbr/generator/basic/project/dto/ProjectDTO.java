package com.dbr.generator.basic.project.dto;

import com.dbr.generator.basic.item.merger.dto.ItemMergerDTO;
import com.dbr.generator.basic.process.dto.ProcessDTO;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectDTO {

    private ProcessDTO processDTO;

    private List<ItemMergerDTO> itemMergerDTOS = new ArrayList<>();

    private List<String> downloadUrls = new ArrayList<>();

    public ProjectDTO(ProcessDTO processDTO,String technicalDescriptor) {
        this.processDTO = processDTO;
        this.technicalDescriptor = technicalDescriptor;
    }

    private String technicalDescriptor;

    public File getProjectFolder() {
        return new File(processDTO.getProcessFolder(), technicalDescriptor);
    }

}
