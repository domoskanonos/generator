package com.dbr.generator.gen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class AbstractGenerator {

    protected static final Logger _log = LoggerFactory.getLogger(AbstractGenerator.class);

    public abstract String create() throws Exception;

    /**
     * Schreibt den generierten Inhalt des Generators in das übergebene Verzeichnis mit dem übergenen Dateinamen.
     *
     * @throws IOException
     */
    public void writeDown(String writeDownPath, String filename) throws Exception {
        File path = new File(writeDownPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(path, filename);
        writeDown(create(), file);
    }

    public void writeDown(String content, File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        Files.write(Paths.get(file.getPath()), content.getBytes(), StandardOpenOption.CREATE);
    }

}
