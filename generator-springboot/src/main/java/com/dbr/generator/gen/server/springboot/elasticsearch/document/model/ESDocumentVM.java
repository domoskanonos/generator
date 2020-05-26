package com.dbr.generator.gen.server.springboot.elasticsearch.document.model;

import com.dbr.generator.basic.model.GeneratorUtil;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class ESDocumentVM {

    private String packageName;
    private String clazzSimpleName;
    private String indexName;

    private List<DocumentPropertie> properties = new ArrayList<>();

    public ESDocumentVM(String clazzSimpleName, Class<?> clazz) {
        this(clazz.getPackage().getName(), clazzSimpleName, clazzSimpleName.toLowerCase(), new ArrayList<>());
        GeneratorUtil.getPrimitivesOnly(clazz).forEach(field -> {
            properties.add(new DocumentPropertie(field.getName(), field.getType().getSimpleName()));
        });
    }

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    public static class DocumentPropertie {
        private String name;
        private String type;
    }

}
