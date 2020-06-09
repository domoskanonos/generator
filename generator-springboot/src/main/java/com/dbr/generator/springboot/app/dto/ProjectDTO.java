package com.dbr.generator.springboot.app.dto;

import com.dbr.generator.basic.enumeration.TemplateEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProjectDTO {

    private Long id;
    private List<ItemDTO> items;
    private Set<TemplateEnum> template;
    private String technicalDescriptor;
    private String javaBasePackage;

    public void addItem(ItemDTO item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }
}