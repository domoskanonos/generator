package com.dbr.generator.gen.server.springboot.model;

import com.dbr.generator.gen.common.csv.compound.model[0].CSV2JavaCVM;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class SpringBootApplicationDataCVM {

    private String systemPackageName;
    private String basePackageName;
    private String entityPackageName;
    private String dtoPackageName;
    private String repositoryPackageName;
    private String servicePackageName;
    private String mappingPackageName;
    private String restPackageName;
    private String testPackageName;

    private List<CSV2JavaCVM> csv2JavaCVMS = new ArrayList<>();

    public SpringBootApplicationDataCVM(String systemPackageName, String basePackageName, String entityPackageName) {
        this.systemPackageName = systemPackageName;
        this.basePackageName = basePackageName;
        this.entityPackageName = entityPackageName;
        this.dtoPackageName = basePackageName + ".dto";
        this.repositoryPackageName = basePackageName + ".repository";
        this.mappingPackageName = basePackageName + ".mapping";
        this.servicePackageName = basePackageName + ".service";
        this.restPackageName = basePackageName + ".rest";
        this.testPackageName = basePackageName + ".test";
    }

    public void addCSVImport(CSV2JavaCVM csv2JavaCVM) {
        this.csv2JavaCVMS.add(csv2JavaCVM);
    }

}
