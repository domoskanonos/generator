package com.dbr.generator.gen.server.springboot.service.jpa.model;

import com.dbr.generator.basic.model.GeneratorUtil;
import com.dbr.generator.basic.model.JavaProperty;
import com.dbr.generator.gen.server.springboot.repository.model.SpringBootJPARepositoryVM;
import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class SpringBootJPAServiceBasicVM {

    public static String SERVICE_NAME_SUFFIX = "BasicService";
    public static String SERVICE_PACKAGE_SUFFIX = ".service";

    private String basePackageName;
    private String packageName;
    private String serviceClazzSimpleName;
    private String repositoryClazzSimpleName;
    private String dtoClazzSimpleName;
    private String jpaClazzSimpleName;
    private String idClazz;

    private List<JavaProperty> properties;

    public SpringBootJPAServiceBasicVM(String basePackageName, String dtoClazzSimpleName, Class<?> entityClazz) {
        this.properties = GeneratorUtil.getJavaProperties(entityClazz);
        String simpleName = entityClazz.getSimpleName();
        this.basePackageName = basePackageName;
        this.packageName = basePackageName + SERVICE_PACKAGE_SUFFIX;
        this.jpaClazzSimpleName = simpleName;
        this.serviceClazzSimpleName = simpleName + SERVICE_NAME_SUFFIX;
        this.repositoryClazzSimpleName = simpleName + SpringBootJPARepositoryVM.REPOSITORY_NAME_SUFFIX;
        this.dtoClazzSimpleName = dtoClazzSimpleName;
        this.idClazz = GeneratorUtil.getIDClazzSimpleName(entityClazz);
    }

}
