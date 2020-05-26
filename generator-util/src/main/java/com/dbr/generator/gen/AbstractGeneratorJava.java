package com.dbr.generator.gen;

import com.dbr.util.resource.ResourceUtil;
import lombok.Data;

@Data
public abstract class AbstractGeneratorJava extends AbstractGenerator {

    private String clazzSimpleName;
    private String packageName;
    protected String writeDownPath;

    public AbstractGeneratorJava(String clazzSimpleName, String packageName) {
        this.clazzSimpleName = clazzSimpleName;
        this.packageName = packageName;
        this.writeDownPath = AbstractGeneratorJava.class.getResource("/").getPath() + "../../src/main/java/";
    }

    public void writeDown() throws Exception {
        String filename = clazzSimpleName + ".java";
        super.writeDown(writeDownPath + ResourceUtil.getPackageNameAsPath(getPackageName()), filename);
    }

}
