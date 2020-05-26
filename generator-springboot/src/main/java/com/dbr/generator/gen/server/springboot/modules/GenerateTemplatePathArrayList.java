package com.dbr.generator.gen.server.springboot.modules;

import com.dbr.generator.gen.server.springboot.modules.model.ModulItem;
import com.dbr.util.resource.ResourceUtil;

import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class GenerateTemplatePathArrayList {


    public static void main(String[] args) {
        File srcFolder = ResourceUtil.getResource("/modules/secure/src/");
        System.out.println("List<ModulItem> items = new ArrayList<>();");
        generateSourceFiles(srcFolder, "secure");
    }

    private static void generateSourceFiles(File file, String moduleName) {
        String absolutePath = file.getAbsolutePath();
        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                generateSourceFiles(childFile, moduleName);
            }
        } else {
            String templatePath = absolutePath.substring(absolutePath.indexOf(moduleName));
            templatePath = templatePath.replace("\\", "/");
            String packageSuffix = templatePath.substring(templatePath.indexOf("src") + 4, templatePath.lastIndexOf("/"));
            packageSuffix = packageSuffix.replace("/", ".");
            System.out.println("items.add(new ModulItem(\"." + packageSuffix + "\", \"" + templatePath + "\", \"" + file.getName().replace(".vm", ".java") + "\"));");
        }
    }

}
