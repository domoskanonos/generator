package com.dbr.generator.gen.server.springboot.elasticsearch.repository.model;

import com.dbr.generator.basic.util.GeneratorUtil;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class ESRepositoryVM {

    private String repositoryPackageName;
    private String repositoryClazzSimpleName;
    private String entityClazzPackageName;
    private String documentClazzName;
    private String idClazz;

    private List<ESRepositoryPropertie> properties = new ArrayList<>();

    public ESRepositoryVM(String repositoryClazzPackageName, String repositoryClazzSimpleName, Class<?> clazz) {
        this(repositoryClazzPackageName, repositoryClazzSimpleName, clazz.getPackage().getName(), clazz.getSimpleName(),
                GeneratorUtil.getIDClazzSimpleName(clazz), new ArrayList<>());
        GeneratorUtil.getPrimitivesOnly(clazz).forEach(field -> {
            properties.add(new ESRepositoryPropertie(field.getName(), field.getType().getSimpleName()));
        });
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ESRepositoryPropertie {
        private String name;
        private String type;
    }

}
