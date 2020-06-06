package com.dbr.generator.springboot.app.dto;

import com.dbr.generator.basic.enumeration.TemplateEnum;
import com.dbr.generator.basic.enumeration.TypeEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ItemDTO {

    private Long id;
    private java.lang.Object projectEntity;
    private String name;
    private TypeEnum idTypeEnum;
    private TemplateEnum template;
    private List<PropertyDTO> properties;

    public void addProperty(PropertyDTO propertyDTO) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }
        this.properties.add(propertyDTO);
    }
}