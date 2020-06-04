package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.model.ProcessModel;
import com.dbr.generator.springboot.app.dto.ProcessDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcessDTOProcessModelMapping {

    public ProcessModel toModel(ProcessDTO source) {
        ProcessModel dest = new ProcessModel();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public ProcessDTO toDTO(ProcessModel source) {
        ProcessDTO dest = new ProcessDTO();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public List<ProcessDTO> toDTOs(Collection<ProcessModel> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ProcessModel> toModels(Collection<ProcessDTO> sources) {
        return sources.stream().map(source -> toModel(source)).collect(Collectors.toList());
    }


}