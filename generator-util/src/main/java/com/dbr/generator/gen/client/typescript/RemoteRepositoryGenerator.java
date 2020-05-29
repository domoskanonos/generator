package com.dbr.generator.gen.client.typescript;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.client.typescript.model[0].RemoteRepositoryVM;
import com.dbr.generator.sample.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

@AllArgsConstructor
public class RemoteRepositoryGenerator extends BasicTypescriptGenerator {

    private RemoteRepositoryVM model;

    public static void main(String[] args) throws Exception {
        RemoteRepositoryVM model = new RemoteRepositoryVM(UserDTO.class, "User");
        RemoteRepositoryGenerator remoteRepositoryGenerator = new RemoteRepositoryGenerator(model);
        remoteRepositoryGenerator.writeDown();
    }

    public void writeDown() throws Exception {
        this.typescriptSuffixPath = "repo/";
        writeDown(model[0].getFilename(), create());
    }

    public String create() throws Exception {

        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate("client/typescript/remote-repository.vm");
        VelocityContext context = new VelocityContext();

        context.put("idClazz", model[0].getIdClazz());
        context.put("remoteRepositoryName", model[0].getRemoteRepositoryName());
        context.put("modelName", model[0].getModelName());
        context.put("modelPath", model[0].getModelPath());
        context.put("restPath", String.format("/%s", model[0].getModelName().toUpperCase()));

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }

}
