package com.dbr.generator.gen.server.springboot.rest.model;

import com.dbr.generator.basic.model.JavaProperty;
import com.dbr.generator.gen.server.dto.model.DTOVM;
import com.dbr.generator.gen.server.springboot.csv.model.SpringBootCSVRestControllerVM;
import com.dbr.generator.gen.server.springboot.service.jpa.model.SpringBootJPAServiceSearchVM;
import com.dbr.generator.basic.model.GeneratorUtil;
import lombok.Data;

import java.util.List;

@Data
public class SpringBootRestControllerSearchVM {

    public static String REST_NAME_SUFFIX = "SearchController";
    public static String REST_PACKAGE_SUFFIX = ".rest";

    private String basePackageName;
    private Class<?> entityClazz;
    private String entityClazzSimpleName;
    private String clazzSimpleName;
    private String idClazzSimpleName;
    private String dtoPackageName;
    private String dtoClazzSimpleName;
    private String servicePackageName;
    private String serviceClazzSimpleName;
    private String restControllerClazzSimpleName;
    private String restControllerPackageName;

    private List<JavaProperty> properties;

    public SpringBootRestControllerSearchVM(String basePackageName, Class<?> entityClazz) {
        this.basePackageName = basePackageName;
        this.entityClazz = entityClazz;

        String entityClazzSimpleName = this.entityClazz.getSimpleName();
        this.entityClazzSimpleName = entityClazzSimpleName;
        this.clazzSimpleName = entityClazzSimpleName + REST_NAME_SUFFIX;
        this.basePackageName = basePackageName;
        this.dtoPackageName = String.format("%s%s", this.basePackageName, DTOVM.DTO_PACKAGE_SUFFIX);
        this.servicePackageName = String.format("%s%s", this.basePackageName,
                SpringBootJPAServiceSearchVM.SERVICE_PACKAGE_SUFFIX);
        this.restControllerPackageName = String.format("%s%s", this.basePackageName,
                SpringBootCSVRestControllerVM.REST_PACKAGE_SUFFIX);
        this.serviceClazzSimpleName = entityClazzSimpleName + SpringBootJPAServiceSearchVM.SERVICE_NAME_SUFFIX;
        this.restControllerClazzSimpleName = entityClazzSimpleName + SpringBootCSVRestControllerVM.REST_NAME_SUFFIX;
        this.dtoClazzSimpleName = entityClazzSimpleName + DTOVM.DTO_NAME_SUFFIX;
        this.idClazzSimpleName = GeneratorUtil.getIDClazzSimpleName(entityClazz);
        this.properties = GeneratorUtil.getJavaProperties(entityClazz);

    }

    public String getRestControllerClazzName() {
        return this.restControllerPackageName + "." + this.restControllerClazzSimpleName;
    }

    public String getServiceClazzName() {
        return this.servicePackageName + "." + this.serviceClazzSimpleName;
    }

    public String getDTOClazzName() {
        return this.dtoPackageName + "." + this.dtoClazzSimpleName;
    }

    public String getPrefixPath() {
        return String.format("%s/SEARCH", getEntityClazzSimpleName().toUpperCase());
    }
}
