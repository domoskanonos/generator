package com.dbr.generator.gen.server.dto.model;

import com.dbr.generator.gen.model.JavaProperty;
import com.dbr.generator.util.generator.GeneratorUtil;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DTOVM {

    static public String DTO_NAME_SUFFIX = "DTO";
    static public String DTO_PACKAGE_SUFFIX = ".dto";

    private String packageName;
    private String clazzSimpleName;
    private List<JavaProperty> properties = new ArrayList<>();

    public DTOVM(String packageName, String clazzSimpleName, Class<?> clazz) {
        this(packageName, clazz);
        this.clazzSimpleName = clazzSimpleName;
    }

    public DTOVM(String packageName, Class<?> clazz) {
        this.packageName = packageName;
        this.clazzSimpleName = clazz.getSimpleName() + DTO_NAME_SUFFIX;
        properties = GeneratorUtil.getJavaProperties(clazz);
    }

    public DTOVM(Class<?> clazz) {
        this.packageName = clazz.getPackage().getName();
        this.clazzSimpleName = clazz.getSimpleName() + DTO_NAME_SUFFIX;
        properties = GeneratorUtil.getJavaProperties(clazz);
    }

    public String getClazzName() {
        return String.format("%s.%s", this.packageName, this.clazzSimpleName);
    }

}
