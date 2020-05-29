package com.dbr.generator.gen.server.springboot.csv.model;

import com.dbr.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class SpringBootCSVRestControllerVM {

    public static String REST_NAME_SUFFIX = "Rest";
    public static String REST_PACKAGE_SUFFIX = ".rest";

    String clazzSimpleName;
    String packageName;
    String csvServicePackageName;
    String csvServiceClazzSimpleName;
    String prefixPath;

}
