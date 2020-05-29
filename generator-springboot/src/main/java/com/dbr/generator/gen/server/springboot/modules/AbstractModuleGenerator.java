package com.dbr.generator.gen.server.springboot.modules;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.server.springboot.modules.model[0].ModulItem;
import com.dbr.generator.gen.server.springboot.modules.model[0].ResourceItem;
import com.dbr.util.resource.ResourceUtil;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public abstract class AbstractModuleGenerator {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    protected String packageName;

    public AbstractModuleGenerator(String packageName) {
        this.packageName = packageName;
    }

    public abstract void generate() throws Exception;

    protected void generate(List<ModulItem> items) throws Exception {
        for (ModulItem item : items) {
            VelocityEngine velocityEngine = VelocityUtil.getEngine();
            Template t = velocityEngine.getTemplate(String.format("modules/%s", item.getTemplateName()));
            VelocityContext context = new VelocityContext();
            context.put("packageName", this.packageName);
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            writeDown(item, writer.toString());
        }
    }

    protected void copyResources(List<ResourceItem> items) throws IOException {
        for (ResourceItem item : items) {
            String templatePath = "/modules/" + item.getModuleName() + "/resources/" + item.getResourcePath();
            InputStream iStream = this.getClass().getResourceAsStream(templatePath);
            File destinationFile = new File(
                    this.getClass().getResource("/").getPath() + "../../src/main/resources/" + item.getResourcePath());
            FileUtils.copyInputStreamToFile(iStream, destinationFile);
        }
    }

    public void writeDown(ModulItem item, String content) throws Exception {
        String path = this.getClass().getResource("/").getPath() + "../../src/main/java/"
                + ResourceUtil.getPackageNameAsPath(this.packageName + item.getPackageSuffix());
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(path, item.getFilename());
        if (file.exists()) {
            file.delete();
        }
        log.info("write file: {}", file.getAbsolutePath());
        Files.write(Paths.get(file.getPath()), content.getBytes(), new OpenOption[] { StandardOpenOption.CREATE });
    }

}
