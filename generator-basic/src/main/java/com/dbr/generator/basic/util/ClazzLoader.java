package com.dbr.generator.basic.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClazzLoader {


    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {

        // Convert File to a URL
        URL url = new File("C:\\_dev\\vhs\\generator\\generator-springboot\\target\\classes").toURI().toURL();
        URL[] urls = new URL[]{url};

        // Create a new class loader with the directory
        ClassLoader cl = new URLClassLoader(urls);

        // Load in the class; MyClass.class should be located in
        // the directory file:/c:/myclasses/com/mycompany
        Class cls = cl.loadClass("com.dbr.generator.springboot.system.auth.entity.AuthUser");
        System.out.println(cls);
    }
}
