package com.dbr.generator.gen.server.springboot.test.base.rest;

import com.dbr.generator.gen.server.springboot.csv.model.SpringBootCSVRestControllerVM;
import com.dbr.generator.gen.server.springboot.rest.model.SpringBootRestControllerBasicVM;
import com.dbr.generator.gen.server.springboot.test.base.SpringBootTestGenerator;
import com.dbr.generator.gen.server.springboot.test.base.model.SpringBootBaseTestVM;

public class SpringBootRestControllerSearchTestGenerator extends SpringBootTestGenerator {

    public SpringBootRestControllerSearchTestGenerator(Class<?> entityClazz, String basePackageName) {
        super(new SpringBootBaseTestVM("sb-test-rest-controller-search.vm",
                SpringBootRestControllerBasicVM.REST_NAME_SUFFIX, SpringBootCSVRestControllerVM.REST_PACKAGE_SUFFIX,
                entityClazz, basePackageName));
    }

}
