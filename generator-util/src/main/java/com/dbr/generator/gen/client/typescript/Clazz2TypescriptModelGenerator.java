package com.dbr.generator.gen.client.typescript;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.client.typescript.model.Clazz2TypescriptModelVM;
import com.dbr.generator.sample.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

@AllArgsConstructor
public class Clazz2TypescriptModelGenerator extends BasicTypescriptGenerator {

    private Clazz2TypescriptModelVM model;

    public static void main(String[] args) throws Exception {
        Clazz2TypescriptModelVM model = new Clazz2TypescriptModelVM(UserDTO.class, "User");
        Clazz2TypescriptModelGenerator clazz2TypescriptModelGenerator = new Clazz2TypescriptModelGenerator(model);
        clazz2TypescriptModelGenerator.writeDown();
    }

    public void writeDown() throws Exception {
        this.typescriptSuffixPath = "model/";
        writeDown(model.getFilename(), create());
    }

    public String create() throws Exception {

        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate("client/typescript/clazz-2-typescript-model.vm");

        VelocityContext context = new VelocityContext();
        context.put("modelName", this.model.getModelName());
        context.put("properties", this.model.getProperties());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }

}
