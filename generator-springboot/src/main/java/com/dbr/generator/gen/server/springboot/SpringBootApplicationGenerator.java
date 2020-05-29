package com.dbr.generator.gen.server.springboot;

import com.dbr.generator.gen.CompoundAbstractGenerator;
import com.dbr.generator.gen.server.mapping.ClazzMappingGenerator;
import com.dbr.generator.gen.server.mapping.model[0].ClazzMappingVM;
import com.dbr.generator.gen.server.springboot.dto.Entity2SwaggerDTOGenerator;
import com.dbr.generator.gen.server.springboot.model[0].SpringBootApplicationDataCVM;
import com.dbr.generator.gen.server.springboot.repository.SpringBootJPARepositoryGenerator;
import com.dbr.generator.gen.server.springboot.repository.model[0].SpringBootJPARepositoryVM;
import com.dbr.generator.gen.server.springboot.rest.SpringBootRestControllerBasicGenerator;
import com.dbr.generator.gen.server.springboot.rest.SpringBootRestControllerSearchGenerator;
import com.dbr.generator.gen.server.springboot.rest.model[0].SpringBootRestControllerBasicVM;
import com.dbr.generator.gen.server.springboot.rest.model[0].SpringBootRestControllerSearchVM;
import com.dbr.generator.gen.server.springboot.service.jpa.SpringBootJPAServiceBasicGenerator;
import com.dbr.generator.gen.server.springboot.service.jpa.SpringBootJPAServiceSearchGenerator;
import com.dbr.generator.gen.server.springboot.service.jpa.model[0].SpringBootJPAServiceBasicVM;
import com.dbr.generator.gen.server.springboot.service.jpa.model[0].SpringBootJPAServiceSearchVM;
import com.dbr.generator.basic.util.GeneratorUtil;
import lombok.Data;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
public class SpringBootApplicationGenerator extends CompoundAbstractGenerator {

    private SpringBootApplicationDataCVM data;

    public SpringBootApplicationGenerator(SpringBootApplicationDataCVM data) {
        super(data.getBasePackageName());
        this.data = data;
    }

    public static void main(String[] args) throws Exception {
        SpringBootApplicationGenerator springBootApplicationGenerator = new SpringBootApplicationGenerator(
                new SpringBootApplicationDataCVM("com.dbr.generator", "com.dbr.generator.result",
                        "com.dbr.generator.sample"));
        springBootApplicationGenerator.writeDown();
    }

    @Override
    public void writeDown() throws Exception {

        List<URL> urls = new ArrayList<>();
        urls.addAll(ClasspathHelper.forJavaClassPath());

        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());
        urls.addAll(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])));

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new TypeAnnotationsScanner())
                .setUrls(urls)
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(this.data.getEntityPackageName()))));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        for (Class<?> aClass : classes) {
            if (aClass.isAnnotationPresent(Entity.class) || aClass.isAnnotationPresent(Embeddable.class)) {
                String simpleName = aClass.getSimpleName();
                log.info("generate class: " + simpleName);
                generate(simpleName, aClass);
            }
        }
    }

    public void generate(String baseName, Class<?> entityClazz) throws Exception {

        String systemPackageName = this.data.getSystemPackageName();
        String dtoPackageName = this.data.getDtoPackageName();
        String restPackageName = this.data.getRestPackageName();
        String mappingPackageName = this.data.getMappingPackageName();
        String repositoryPackageName = this.data.getRepositoryPackageName();
        String testPackageName = this.data.getTestPackageName();

        String dtoClazzSimpleName = baseName + "DTO";
        String dtoClazzName = dtoPackageName + "." + baseName + "DTO";
        Entity2SwaggerDTOGenerator entity2SwaggerDTOGenerator = new Entity2SwaggerDTOGenerator(dtoClazzSimpleName,
                dtoPackageName, entityClazz);
        entity2SwaggerDTOGenerator.writeDown();

        if (entityClazz.isAnnotationPresent(Embeddable.class)) {
            return;
        }

        SpringBootJPARepositoryGenerator springBootJPARepositoryGenerator = new SpringBootJPARepositoryGenerator(
                new SpringBootJPARepositoryVM(repositoryPackageName, entityClazz));
        springBootJPARepositoryGenerator.writeDown();

        String entityClazzPackageName = entityClazz.getPackage().getName();
        String jpaClazzSimpleName = entityClazz.getSimpleName();
        ClazzMappingVM clazzMappingVM = new ClazzMappingVM("sb-clazz-mapping.vm", mappingPackageName,
                jpaClazzSimpleName + dtoClazzSimpleName + "Mapping", entityClazzPackageName, jpaClazzSimpleName,
                dtoPackageName, dtoClazzSimpleName, GeneratorUtil.getJavaProperties(entityClazz));
        ClazzMappingGenerator clazzMappingGenerator = new ClazzMappingGenerator(clazzMappingVM);
        clazzMappingGenerator.writeDown();

        SpringBootJPAServiceBasicVM springBootJpaServiceBasicVM = new SpringBootJPAServiceBasicVM(basePackageName,
                dtoClazzSimpleName, entityClazz);
        SpringBootJPAServiceBasicGenerator springBootJPAServiceBasicGenerator = new SpringBootJPAServiceBasicGenerator(
                springBootJpaServiceBasicVM);
        springBootJPAServiceBasicGenerator.writeDown();

        SpringBootJPAServiceSearchVM springBootJpaServiceSearchVM = new SpringBootJPAServiceSearchVM(systemPackageName,
                basePackageName, dtoClazzSimpleName, entityClazz);
        SpringBootJPAServiceSearchGenerator springBootJPAServiceSearchGenerator = new SpringBootJPAServiceSearchGenerator(
                springBootJpaServiceSearchVM);
        springBootJPAServiceSearchGenerator.writeDown();

        SpringBootRestControllerBasicGenerator springBootRestControllerBasicGenerator = new SpringBootRestControllerBasicGenerator(
                new SpringBootRestControllerBasicVM(basePackageName, entityClazz));
        springBootRestControllerBasicGenerator.writeDown();

        SpringBootRestControllerSearchGenerator springBootRestControllerSearchGenerator = new SpringBootRestControllerSearchGenerator(
                new SpringBootRestControllerSearchVM(basePackageName, entityClazz));
        springBootRestControllerSearchGenerator.writeDown();

        String restControllerClazzSimpleName = baseName + "RestController";
        String serviceClazzName = springBootJpaServiceBasicVM.getPackageName() + "."
                + springBootJpaServiceBasicVM.getServiceClazzSimpleName();
        String serviceClazzSimpleName = springBootJpaServiceBasicVM.getServiceClazzSimpleName();

        // RestControllerTestGenerator restControllerTestGenerator = new RestControllerTestGenerator(testPackageName,
        // restPackageName + "." + restControllerClazzSimpleName, restControllerClazzSimpleName, serviceClazzName,
        // serviceClazzSimpleName, dtoClazzName, dtoClazzSimpleName, entityClazz);
        // restControllerTestGenerator.writeDown();

        // SpringBootServiceTestGenerator springBootServiceTestGenerator = new
        // SpringBootServiceTestGenerator(testPackageName, restPackageName + "." + restControllerClazzSimpleName,
        // restControllerClazzSimpleName, serviceClazzName, serviceClazzSimpleName, dtoClazzName, dtoClazzSimpleName,
        // entityClazz);
        // springBootServiceTestGenerator.writeDown();

    }

}
