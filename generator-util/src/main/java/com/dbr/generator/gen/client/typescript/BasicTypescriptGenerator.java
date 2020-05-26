package com.dbr.generator.gen.client.typescript;

import com.dbr.generator.gen.AbstractGeneratorJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BasicTypescriptGenerator {

    protected static final Logger _log = LoggerFactory.getLogger(BasicTypescriptGenerator.class);

    public static String TYPESCRIPT_SOURCE_PATH_EXTENDED = AbstractGeneratorJava.class.getResource("/").getPath()
            + "../../source/";

    protected String typescriptSuffixPath = "";

    public static void writeDownExternal(File file, String content) throws Exception {
        Path path = Paths.get(file.getPath());
        if (file.exists()) {
            Files.delete(path);
        }
        Files.write(path, content.getBytes(), StandardOpenOption.CREATE);
    }

    public void writeDown(File file, String content) throws Exception {
        Path path = Paths.get(file.getPath());
        if (file.exists()) {
            Files.delete(path);
        }
        Files.write(path, content.getBytes(), StandardOpenOption.CREATE);
    }

    public void writeDown(String filename, String content) throws Exception {
        File destinationFile = new File(getDestinationFolder(), filename);
        this.writeDown(destinationFile, content);
    }

    public File getDestinationFolder() {
        File destinationFolder = new File(new StringBuilder().append(this.TYPESCRIPT_SOURCE_PATH_EXTENDED)
                .append(this.typescriptSuffixPath).toString());
        _log.info(String.format("write down destination folder: %s", destinationFolder.getAbsolutePath()));
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }
        return destinationFolder;
    }

}
