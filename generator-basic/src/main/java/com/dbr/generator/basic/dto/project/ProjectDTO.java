package com.dbr.generator.basic.dto.project;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.ProcessDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProjectDTO {

    private String technicalDescriptor;

    private ProcessDTO processDTO;

    private List<ItemDTO> items = new ArrayList<>();

    public ProjectDTO(ProcessDTO processDTO,String technicalDescriptor) {
        this.processDTO = processDTO;
        this.technicalDescriptor = technicalDescriptor;
    }

    public File getProjectFolder() {
        return new File(processDTO.getProcessFolder(), new StringBuilder().append(processDTO.getProjectFolderPrefix()).append(technicalDescriptor).toString());
    }

}
