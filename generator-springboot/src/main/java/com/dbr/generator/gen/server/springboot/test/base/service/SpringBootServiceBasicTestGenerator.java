package com.dbr.generator.gen.server.springboot.test.base.service;

import com.dbr.generator.gen.server.springboot.service.jpa.model.SpringBootJPAServiceBasicVM;
import com.dbr.generator.gen.server.springboot.test.base.SpringBootTestGenerator;
import com.dbr.generator.gen.server.springboot.test.base.model.SpringBootBaseTestVM;

public class SpringBootServiceBasicTestGenerator extends SpringBootTestGenerator {

    public SpringBootServiceBasicTestGenerator(Class<?> entityClazz, String basePackageName) {
        super(new SpringBootBaseTestVM("sb-test-service-basic.vm", SpringBootJPAServiceBasicVM.SERVICE_NAME_SUFFIX, SpringBootJPAServiceBasicVM.SERVICE_PACKAGE_SUFFIX, entityClazz, basePackageName));
    }

}
