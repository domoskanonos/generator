package com.dbr.generator.springboot.app.dto;

import com.dbr.generator.basic.enumeration.Template;
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
    private Set<Template> template;
    private String technicalDescriptor;
    private String namespace;
    private Boolean deactivated;

    public void addItem(ItemDTO item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }
}