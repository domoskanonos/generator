package com.dbr.generator.basic.meta;

import com.dbr.generator.basic.generator.process.ProcessGenerator;
import com.dbr.generator.basic.model.ProcessModel;
import com.dbr.generator.basic.model.project.NidocaProjectModel;
import com.dbr.generator.basic.model.project.SpringBootProjectModel;
import com.dbr.generator.jpa.DatabaseJPAUtil;

public class JavaDatabaseFullProcess extends ProcessModel {

    public static void main(String[] args) throws Exception {
        String technicalDescriptor = "weightmanager";
        String processTempPath = new StringBuilder().append(System.getProperty("java.io.tmpdir")).append(technicalDescriptor).toString();
        String processParentPath = "C:\\_dev\\vhs\\git";
        String javaBasePackage = "com.dbr.weightmanager";
        DatabaseJPAUtil.DatabaseType databaseType = DatabaseJPAUtil.DatabaseType.MYSQL;
        String databaseHost = "nidoca.de";
        int databasePort = 3306;
        String databaseUser = "root";
        String databasePSWD = "tgz014vb";
        ProcessModel model = new JavaDatabaseFullProcess(processTempPath, processParentPath, technicalDescriptor, javaBasePackage, databaseType, databaseHost, databasePort, databaseUser, databasePSWD, technicalDescriptor, "");
        ProcessGenerator.generate(model);
    }


    public JavaDatabaseFullProcess(String processTempPath, String processParentPath, String technicalDescriptor, String javaBasePackage, DatabaseJPAUtil.DatabaseType databaseType, String host, int port, String username, String password, String catalog, String schema) throws ClassNotFoundException {
        super(processTempPath, processParentPath, technicalDescriptor);
        DatabaseJPAUtil databaseJPAUtil = new DatabaseJPAUtil(databaseType, host, port, username, password, catalog, schema);

        SpringBootProjectModel springBootProjectModel = new SpringBootProjectModel(this, "springboot", javaBasePackage);
        springBootProjectModel.setAddSpringBootSecurityModule(true);
        getProjects().add(springBootProjectModel);


        NidocaProjectModel nidocaProjectModel = new NidocaProjectModel(this, "nidoca");
        getProjects().add(nidocaProjectModel);


    }


}
