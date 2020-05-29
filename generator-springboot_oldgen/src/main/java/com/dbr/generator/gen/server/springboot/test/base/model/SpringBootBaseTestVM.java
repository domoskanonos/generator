package com.dbr.generator.gen.server.springboot.test.base.model;

import com.dbr.generator.basic.property.dto.PropertyDTO;
import com.dbr.generator.gen.server.dto.model.DTOVM;
import com.dbr.generator.gen.server.springboot.rest.model.SpringBootRestControllerBasicVM;
import com.dbr.generator.gen.server.springboot.service.jpa.model.SpringBootJPAServiceBasicVM;
import com.dbr.generator.gen.server.springboot.test.TestVM;
import com.dbr.generator.basic.util.GeneratorUtil;
import lombok.Data;

import java.util.List;

@Data
public class SpringBootBaseTestVM extends TestVM {

    private String templateName;
    private Class<?> entityClazz;
    private String clazzSimpleName;
    private String entityClazzSimpleName;
    private String basePackageName;
    private String testPackageName;
    private String idClazzSimpleName;
    private String dtoPackageName;
    private String dtoClazzSimpleName;
    private String servicePackageName;
    private String serviceClazzSimpleName;
    private String restControllerClazzSimpleName;
    private String restControllerPackageName;

    private List<PropertyDTO> properties;

    public SpringBootBaseTestVM(String templateName, String testClazzMiddleName, String testPackageSuffix,
            Class<?> entityClazz, String basePackageName) {
        this.templateName = templateName;
        this.entityClazz = entityClazz;
        this.entityClazzSimpleName = this.entityClazz.getSimpleName();
        this.clazzSimpleName = this.entityClazzSimpleName + testClazzMiddleName + CLAZZ_NAME_SUFFIX;
        this.basePackageName = basePackageName;
        this.testPackageName = basePackageName + testPackageSuffix;
        this.dtoPackageName = String.format("%s%s", basePackageName, DTOVM.DTO_PACKAGE_SUFFIX);
        this.servicePackageName = String.format("%s%s", basePackageName,
                SpringBootJPAServiceBasicVM.SERVICE_PACKAGE_SUFFIX);
        this.restControllerPackageName = String.format("%s%s", basePackageName,
                SpringBootRestControllerBasicVM.REST_PACKAGE_SUFFIX);
        this.serviceClazzSimpleName = this.entityClazzSimpleName + SpringBootJPAServiceBasicVM.SERVICE_NAME_SUFFIX;
        this.restControllerClazzSimpleName = this.entityClazzSimpleName
                + SpringBootRestControllerBasicVM.REST_NAME_SUFFIX;
        this.dtoClazzSimpleName = this.entityClazzSimpleName + DTOVM.DTO_NAME_SUFFIX;
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

}
