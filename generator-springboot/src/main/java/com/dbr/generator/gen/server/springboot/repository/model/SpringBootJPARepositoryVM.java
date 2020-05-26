package com.dbr.generator.gen.server.springboot.repository.model;

import com.dbr.generator.util.generator.GeneratorUtil;
import com.dbr.generator.gen.model.JavaProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class SpringBootJPARepositoryVM {

    public static String REPOSITORY_PACKAGE_SUFFIX = ".repository";
    public static String REPOSITORY_NAME_SUFFIX = "Repository";

    private String repositoryPackageName;
    private String repositoryClazzSimpleName;
    private String entityClazzPackageName;
    private String jpaClazzSimpleName;
    private String idClazz;
    private Boolean generateSinglePropertieQuerys;
    private Boolean generateAllPropertieQuery;
    private List<JavaProperty> properties;

    public SpringBootJPARepositoryVM(String repositoryClazzPackageName, Class<?> clazz) {
        this(repositoryClazzPackageName, clazz.getSimpleName() + REPOSITORY_NAME_SUFFIX, clazz.getPackage().getName(),
                clazz.getSimpleName(), GeneratorUtil.getIDClazzSimpleName(clazz), true, true, new ArrayList<>());
        GeneratorUtil.getPrimitivesOnly(clazz).forEach(field -> {
            properties.add(new JavaProperty(field.getName(), field.getType().getSimpleName()));
        });
    }

}
