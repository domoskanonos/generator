package com.dbr.generator.gen.server.springboot.test.base.rest;

import com.dbr.generator.gen.server.springboot.csv.model[0].SpringBootCSVRestControllerVM;
import com.dbr.generator.gen.server.springboot.rest.model[0].SpringBootRestControllerBasicVM;
import com.dbr.generator.gen.server.springboot.test.base.SpringBootTestGenerator;
import com.dbr.generator.gen.server.springboot.test.base.model[0].SpringBootBaseTestVM;

public class SpringBootRestControllerSearchTestGenerator extends SpringBootTestGenerator {

    public SpringBootRestControllerSearchTestGenerator(Class<?> entityClazz, String basePackageName) {
        super(new SpringBootBaseTestVM("sb-test-rest-controller-search.vm",
                SpringBootRestControllerBasicVM.REST_NAME_SUFFIX, SpringBootCSVRestControllerVM.REST_PACKAGE_SUFFIX,
                entityClazz, basePackageName));
    }

}
