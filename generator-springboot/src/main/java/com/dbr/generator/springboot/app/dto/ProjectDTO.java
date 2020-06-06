package com.dbr.generator.springboot.app.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProjectDTO {

    private Long id;
    private List<ItemDTO> items;
    private java.lang.Object template;
    private String technicalDescriptor;
    private String javaBasePackage;

    public void addItem(ItemDTO item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }
}