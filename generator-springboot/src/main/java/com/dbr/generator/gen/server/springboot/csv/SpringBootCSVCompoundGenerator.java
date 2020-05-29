package com.dbr.generator.gen.server.springboot.csv;

import com.dbr.generator.gen.CompoundAbstractGenerator;
import com.dbr.generator.gen.server.springboot.csv.model[0].SpringBootCSVCVM;
import com.dbr.generator.gen.server.springboot.csv.model[0].SpringBootCSVRestControllerVM;
import com.dbr.generator.gen.server.springboot.csv.model[0].SpringBootCSVServiceVM;
import com.dbr.util.StringUtil;

public class SpringBootCSVCompoundGenerator extends CompoundAbstractGenerator {

    private SpringBootCSVCVM model;

    public static void main(String[] args) throws Exception {
        SpringBootCSVCVM model = new SpringBootCSVCVM("Test", "com.dbr.generator.result");
        SpringBootCSVCompoundGenerator springBootCSVCompoundGenerator = new SpringBootCSVCompoundGenerator(model);
        springBootCSVCompoundGenerator.writeDown();
    }

    public SpringBootCSVCompoundGenerator(SpringBootCSVCVM model) {
        super(model[0].getBasePackageName());
        this.model = model;
    }

    @Override
    public void writeDown() throws Exception {

        String clazzSimpleBaseName = this.model[0].getClazzSimpleBaseName();
        String serviceClazzSimpleName = String.format("%sCSVService", clazzSimpleBaseName);
        String servicePackageName = String.format("%s.service", getBasePackageName());
        String csvImporterPackageName = String.format("%s.csv", getBasePackageName());
        String csvImporterClazzSimpleName = String.format("%sCSVImporter", clazzSimpleBaseName);
        String dtoPackageName = String.format("%s.dto", getBasePackageName());
        String dtoClazzSimpleName = String.format("%sDTO", clazzSimpleBaseName);
        String jpaRepositoryPackageName = String.format("%s.repository", getBasePackageName());
        String jpaRepositoryClazzSimpleName = String.format("%sRepository", clazzSimpleBaseName);
        String mapperClazzSimpleName = String.format("%s%sMapping", clazzSimpleBaseName, dtoClazzSimpleName);
        String mappingPackageName = String.format("%s.mapping", basePackageName);

        SpringBootCSVServiceVM springBootCSVServiceVM = new SpringBootCSVServiceVM(serviceClazzSimpleName,
                servicePackageName, clazzSimpleBaseName, csvImporterPackageName, csvImporterClazzSimpleName,
                dtoPackageName, dtoClazzSimpleName, jpaRepositoryPackageName, jpaRepositoryClazzSimpleName,
                mappingPackageName, mapperClazzSimpleName);

        SpringBootCSVServiceGenerator springBootCSVServiceGenerator = new SpringBootCSVServiceGenerator(
                springBootCSVServiceVM);
        springBootCSVServiceGenerator.writeDown();

        String controllerClazzSimpleName = String.format("%sCSVRestController", clazzSimpleBaseName);
        String controllerPackageName = String.format("%s.rest", basePackageName);

        SpringBootCSVRestControllerVM springBootCSVRestControllerVM = new SpringBootCSVRestControllerVM(
                controllerClazzSimpleName, controllerPackageName, servicePackageName, serviceClazzSimpleName,
                StringUtil.toUpperCase(clazzSimpleBaseName));

        SpringBootCSVRestControllerGenerator springBootCSVRestControllerGenerator = new SpringBootCSVRestControllerGenerator(
                springBootCSVRestControllerVM);
        springBootCSVRestControllerGenerator.writeDown();

    }

}
