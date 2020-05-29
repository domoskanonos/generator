package com.dbr.generator.gen.server.springboot.test.base.service;

import com.dbr.generator.gen.server.springboot.service.jpa.model.SpringBootJPAServiceBasicVM;
import com.dbr.generator.gen.server.springboot.test.base.SpringBootTestGenerator;
import com.dbr.generator.gen.server.springboot.test.base.model.SpringBootBaseTestVM;

public class SpringBootServiceSearchTestGenerator extends SpringBootTestGenerator {

    public SpringBootServiceSearchTestGenerator(Class<?> entityClazz, String basePackageName) {
        super(new SpringBootBaseTestVM("sb-test-service-search.vm", SpringBootJPAServiceBasicVM.SERVICE_NAME_SUFFIX,
                SpringBootJPAServiceBasicVM.SERVICE_PACKAGE_SUFFIX, entityClazz, basePackageName));
    }

}
