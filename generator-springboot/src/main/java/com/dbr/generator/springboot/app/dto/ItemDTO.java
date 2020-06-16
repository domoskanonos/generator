package com.dbr.generator.springboot.app.dto;

import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.enumeration.PropertyType;
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
    private PropertyType idPropertyType;
    private List<PropertyDTO> properties = new ArrayList<>();
    private Set<Template> template = new HashSet<>();
    private Boolean deactivated;

    public void addProperty(PropertyDTO propertyDTO) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }
        this.properties.add(propertyDTO);
    }
}