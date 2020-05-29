package com.dbr.generator.gen.client.typescript.model;

import com.dbr.generator.basic.converter.JavaField2PropertyDTOConverter;
import com.dbr.generator.basic.dto.ConverterDTO;
import com.dbr.generator.basic.dto.ObjectDTO;
import com.dbr.generator.basic.dto.ProjectDTO;
import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.generator.basic.dto.PropertyDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TypescriptBaseVM {

    private Class modelClazz;
    private String modelName;
    private String modelPath;
    private List<PropertyDTO> properties;

    public TypescriptBaseVM(Class modelClazz, String modelName) {
        this.modelClazz = modelClazz;
        this.modelName = modelName;
        this.modelPath = String.format("../model/%s-model", this.modelName.toLowerCase());
       // this.properties = new JavaField2PropertyDTOConverter().convert(new ConverterDTO(new ObjectDTO(), Arrays.asList(modelClazz.getDeclaredFields())));
    }

}
