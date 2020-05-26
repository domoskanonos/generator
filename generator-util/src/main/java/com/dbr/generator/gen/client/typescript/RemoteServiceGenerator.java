package com.dbr.generator.gen.client.typescript;

import com.dbr.generator.VelocityUtil;
import com.dbr.generator.gen.client.typescript.model.RemoteServiceVM;
import com.dbr.generator.sample.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

@AllArgsConstructor
public class RemoteServiceGenerator extends BasicTypescriptGenerator {

    private RemoteServiceVM model;

    public static void main(String[] args) throws Exception {
        RemoteServiceVM model = new RemoteServiceVM(UserDTO.class, "User");
        RemoteServiceGenerator generator = new RemoteServiceGenerator(model);
        generator.writeDown();
    }

    public void writeDown() throws Exception {
        this.typescriptSuffixPath = "service/";
        writeDown(model.getFilename(), create());
    }

    public String create() throws Exception {

        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate("client/typescript/remote-service.vm");
        VelocityContext context = new VelocityContext();

        context.put("remoteServiceName", model.getRemoteServiceName());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }

}
