package com.dbr.generator.basic.util;

import java.io.File;

public class ValidationUtil {

    public static void validateDirectoryPathNotEqual(File folderOne, File folderTwo) {
        if (folderOne.getAbsolutePath().equals(folderTwo.getAbsolutePath())) {
            throw new RuntimeException(new StringBuilder().append("same folder error: ").append(folderOne.getAbsolutePath()).toString());
        }
    }

    public static void validateNotHDDBaseDirectory(File folder) {
        if (folder.getAbsolutePath().length() < 4) {
            throw new RuntimeException(String.format("root folder can't be hdd base directory: %s", folder.getAbsolutePath()));
        }
    }


}
