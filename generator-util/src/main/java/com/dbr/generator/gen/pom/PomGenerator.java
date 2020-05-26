package com.dbr.generator.gen.pom;

import com.dbr.generator.basic.VelocityUtil;
import com.dbr.generator.gen.AbstractGenerator;
import com.dbr.generator.gen.pom.model.PomVM;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class PomGenerator extends AbstractGenerator {

    private PomVM model;

    public PomGenerator(PomVM model) {
        this.model = model;
    }

    @Override
    public String create() {

        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate("pom.vm");
        VelocityContext context = new VelocityContext();

        context.put("groupId", this.model.getGroupId());
        context.put("artifactId", this.model.getArtifactId());
        context.put("version", this.model.getVersion());
        context.put("name", this.model.getName());
        context.put("url", this.model.getUrl());
        context.put("encoding", this.model.getEncoding());
        context.put("javaVersionSource", this.model.getJavaVersionSource());
        context.put("javaVersionTarget", this.model.getJavaVersionTarget());
        context.put("dependencys", this.model.getDependencys());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }

}
