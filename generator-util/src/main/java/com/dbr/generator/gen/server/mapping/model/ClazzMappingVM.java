package com.dbr.generator.gen.server.mapping.model;

import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.util.GeneratorUtil;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClazzMappingVM {

    public static String MAPPING_PACKAGE_SUFFIX = ".mapping";
    public static String MAPPING_NAME_SUFFIX = "Mapping";

    private String template = "common/clazz-mapping.vm";
    private String packageName;
    private String clazzSimpleName;
    private String firstClazzPackageName;
    private String firstClazzSimpleName;
    private String secondClazzPackageName;
    private String secondClazzSimpleName;

    private List<PropertyDTO> properties = new ArrayList<>();

    public ClazzMappingVM(String packageName, Class<?> firstClazz, Class<?> secondClazz, String template) {
        this(packageName, firstClazz, secondClazz);
        this.template = template;
    }

    public ClazzMappingVM(String packageName, Class<?> firstClazz, Class<?> secondClazz) {
        this.packageName = packageName;
        this.firstClazzPackageName = firstClazz.getPackage().getName();
        this.firstClazzSimpleName = firstClazz.getSimpleName();
        this.secondClazzPackageName = secondClazz.getPackage().getName();
        this.secondClazzSimpleName = secondClazz.getSimpleName();
        this.clazzSimpleName = this.firstClazzSimpleName + this.secondClazzSimpleName + MAPPING_NAME_SUFFIX;
        GeneratorUtil.getPrimitivesOnly(firstClazz).forEach(field -> {
            properties.add(new PropertyDTO(field.getName(), field.getType().getName()));
        });
    }

    public String getFirstClazzName() {
        return this.firstClazzPackageName + "." + this.firstClazzSimpleName;
    }

    public String getSecondClazzName() {
        return this.secondClazzPackageName + "." + this.secondClazzSimpleName;
    }

}
