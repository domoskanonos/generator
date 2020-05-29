package com.dbr.generator.gen.server.springboot.crud;

import com.dbr.generator.gen.CompoundAbstractGenerator;
import com.dbr.generator.gen.server.dto.DTOGenerator;
import com.dbr.generator.gen.server.mapping.ClazzMappingGenerator;
import com.dbr.generator.gen.server.springboot.crud.model.SpringBootCRUDCVM;
import com.dbr.generator.gen.server.springboot.repository.SpringBootJPARepositoryGenerator;
import com.dbr.generator.gen.server.springboot.rest.SpringBootRestControllerBasicGenerator;
import com.dbr.generator.gen.server.springboot.service.jpa.SpringBootJPAServiceBasicGenerator;
import com.dbr.generator.gen.server.springboot.test.base.rest.SpringBootRestControllerBasicTestGenerator;
import com.dbr.generator.gen.server.springboot.test.base.service.SpringBootServiceBasicTestGenerator;
import com.dbr.generator.sample.entity.Example;

public class SpringBootCRUDGenerator extends CompoundAbstractGenerator {

    public static void main(String[] args) throws Exception {
        SpringBootCRUDGenerator springBootCRUDGenerator = new SpringBootCRUDGenerator(
                new SpringBootCRUDCVM("com.dbr.generator.result.example", Example.class));
        springBootCRUDGenerator.writeDown();
    }

    private SpringBootCRUDCVM data;

    public SpringBootCRUDGenerator(SpringBootCRUDCVM data) {
        super(data.getBasePackageName());
        this.data = data;
    }

    @Override
    public void writeDown() throws Exception {

        if (this.data.isGenerateDTO()) {
            DTOGenerator dtoGenerator = new DTOGenerator(this.data.getDtovm());
            dtoGenerator.writeDown();
        }

        if (this.data.isGenerateDTO() && this.data.isGenerateEntityDTOMapping()) {
            ClazzMappingGenerator clazzMappingGenerator = new ClazzMappingGenerator(this.data.getClazzMappingVM());
            clazzMappingGenerator.writeDown();
        }

        if (this.data.isGenerateJPARepository()) {
            SpringBootJPARepositoryGenerator springBootJPARepositoryGenerator = new SpringBootJPARepositoryGenerator(
                    this.data.getSpringBootJPARepositoryVM());
            springBootJPARepositoryGenerator.writeDown();
        }

        if (this.data.isGenerateJPAServiceBasic()) {
            SpringBootJPAServiceBasicGenerator springBootJPAServiceBasicGenerator = new SpringBootJPAServiceBasicGenerator(
                    this.data.getSpringBootJPAServiceBasicVM());
            springBootJPAServiceBasicGenerator.writeDown();
        }

        if (this.data.isGenerateJPAServiceBasicTest()) {
            SpringBootServiceBasicTestGenerator springBootServiceBasicTestGenerator = new SpringBootServiceBasicTestGenerator(
                    this.data.getEntityClazz(), this.basePackageName);
            springBootServiceBasicTestGenerator.writeDown();
        }

        if (this.data.isGenerateRestControllerBasic()) {
            SpringBootRestControllerBasicGenerator springBootRestControllerBasicGenerator = new SpringBootRestControllerBasicGenerator(
                    this.data.getSpringBootRestControllerBasicVM());
            springBootRestControllerBasicGenerator.writeDown();
        }

        if (this.data.isGenerateRestControllerBasicTest()) {
            SpringBootRestControllerBasicTestGenerator springBootRestControllerBasicTestGenerator = new SpringBootRestControllerBasicTestGenerator(
                    this.data.getEntityClazz(), this.basePackageName);
            springBootRestControllerBasicTestGenerator.writeDown();
        }

    }

}
