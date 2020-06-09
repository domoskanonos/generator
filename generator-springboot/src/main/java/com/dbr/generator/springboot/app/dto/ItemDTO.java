package com.dbr.generator.springboot.app.dto;

import com.dbr.generator.basic.enumeration.TemplateEnum;
import com.dbr.generator.basic.enumeration.TypeEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<PropertyDTO> properties;
    private Set<TemplateEnum> template = new HashSet<>();
    private Boolean deactivated;

    public void addProperty(PropertyDTO propertyDTO) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }
        this.properties.add(propertyDTO);
    }
}