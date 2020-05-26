package com.dbr.generator.gen.pom.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class PomVM {

    private String groupId;
    private String artifactId;
    private String version;
    private String name;
    private String url;
    private String encoding;
    private String javaVersionSource;
    private String javaVersionTarget;
    private List<PomDependencyModel> dependencys = new ArrayList<>();

}
