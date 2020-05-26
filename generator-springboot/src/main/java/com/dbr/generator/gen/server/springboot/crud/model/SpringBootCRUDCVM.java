package com.dbr.generator.gen.server.springboot.crud.model;

import com.dbr.generator.gen.server.dto.model.DTOVM;
import com.dbr.generator.gen.server.mapping.model.ClazzMappingVM;
import com.dbr.generator.gen.server.springboot.repository.model.SpringBootJPARepositoryVM;
import com.dbr.generator.gen.server.springboot.rest.model.SpringBootRestControllerBasicVM;
import com.dbr.generator.gen.server.springboot.service.jpa.model.SpringBootJPAServiceBasicVM;
import com.dbr.generator.util.generator.GeneratorUtil;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SpringBootCRUDCVM {

    private String basePackageName;
    private Class<?> entityClazz;
    private DTOVM dtovm;
    private ClazzMappingVM clazzMappingVM;
    private SpringBootJPARepositoryVM springBootJPARepositoryVM;
    private SpringBootJPAServiceBasicVM springBootJPAServiceBasicVM;
    private SpringBootRestControllerBasicVM springBootRestControllerBasicVM;

    private boolean generateDTO = true;
    private boolean generateEntityDTOMapping = true;
    private boolean generateJPARepository = true;
    private boolean generateJPAServiceBasic = true;
    private boolean generateJPAServiceBasicTest = true;
    private boolean generateRestControllerBasic = true;
    private boolean generateRestControllerBasicTest = true;

    public SpringBootCRUDCVM(String basePackageName, Class<?> entityClazz) {
        this.basePackageName = basePackageName;
        this.entityClazz = entityClazz;
        this.dtovm = new DTOVM(String.format("%s%s", this.basePackageName, DTOVM.DTO_PACKAGE_SUFFIX), entityClazz);
        this.clazzMappingVM = new ClazzMappingVM("sb-clazz-mapping.vm",
                this.basePackageName + ClazzMappingVM.MAPPING_PACKAGE_SUFFIX,
                entityClazz.getSimpleName() + dtovm.getClazzSimpleName() + ClazzMappingVM.MAPPING_NAME_SUFFIX,
                entityClazz.getPackage().getName(), entityClazz.getSimpleName(), this.dtovm.getPackageName(),
                this.dtovm.getClazzSimpleName(), GeneratorUtil.getJavaProperties(entityClazz, true));
        this.springBootJPARepositoryVM = new SpringBootJPARepositoryVM(
                this.basePackageName + SpringBootJPARepositoryVM.REPOSITORY_PACKAGE_SUFFIX, entityClazz);
        this.springBootJPAServiceBasicVM = new SpringBootJPAServiceBasicVM(
                this.basePackageName + SpringBootJPAServiceBasicVM.SERVICE_PACKAGE_SUFFIX,
                this.dtovm.getClazzSimpleName(), entityClazz);
        this.springBootRestControllerBasicVM = new SpringBootRestControllerBasicVM(this.basePackageName, entityClazz);
    }

}
