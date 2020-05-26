package com.dbr.generator.gen.server.springboot.service.jpa.model;

import com.dbr.generator.gen.model.JavaProperty;
import com.dbr.generator.gen.server.springboot.repository.model.SpringBootJPARepositoryVM;
import com.dbr.generator.util.generator.GeneratorUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class SpringBootJPAServiceSearchVM {

    public static String SERVICE_NAME_SUFFIX = "SearchService";
    public static String SERVICE_PACKAGE_SUFFIX = ".service";

    private String systemPackageName;
    private String basePackageName;
    private String packageName;
    private String serviceClazzSimpleName;
    private String repositoryClazzSimpleName;
    private String dtoClazzSimpleName;
    private String jpaClazzSimpleName;
    private String idClazz;

    private List<JavaProperty> properties;

    public SpringBootJPAServiceSearchVM(String systemPackageName, String basePackageName, String dtoClazzSimpleName, Class<?> entityClazz) {
        this.systemPackageName = systemPackageName;
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
