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

    public ProjectDTO(ProcessDTO processDTO,String technicalDescriptor, String javaBasePackage) {
        this.processDTO = processDTO;
        this.technicalDescriptor = technicalDescriptor;
        this.javaBasePackage = javaBasePackage;
    }

    private String technicalDescriptor;

    private String javaBasePackage;

    private List<ItemMergerDTO> itemMergerDTOS = new ArrayList<>();

    public File getProjectFolder() {
        return new File(processDTO.getProcessFolder(), technicalDescriptor);
    }

}
