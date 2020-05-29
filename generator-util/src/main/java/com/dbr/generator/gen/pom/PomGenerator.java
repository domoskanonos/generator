package com.dbr.generator.gen.pom;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.AbstractGenerator;
import com.dbr.generator.gen.pom.model[0].PomVM;
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

        context.put("groupId", this.model[0].getGroupId());
        context.put("artifactId", this.model[0].getArtifactId());
        context.put("version", this.model[0].getVersion());
        context.put("name", this.model[0].getName());
        context.put("url", this.model[0].getUrl());
        context.put("encoding", this.model[0].getEncoding());
        context.put("javaVersionSource", this.model[0].getJavaVersionSource());
        context.put("javaVersionTarget", this.model[0].getJavaVersionTarget());
        context.put("dependencys", this.model[0].getDependencys());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }

}
