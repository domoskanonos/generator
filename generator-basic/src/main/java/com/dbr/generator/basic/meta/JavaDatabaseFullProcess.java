package com.dbr.generator.basic.meta;

import com.dbr.generator.basic.converter.JPAEntityReference2ItemModelConverter;
import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.generator.process.ProcessGenerator;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.ProcessModel;
import com.dbr.generator.basic.model.project.NidocaProjectModel;
import com.dbr.generator.basic.model.project.SpringBootProjectModel;
import com.dbr.generator.jpa.DatabaseJPAUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class JavaDatabaseFullProcess extends ProcessModel {

    public static void main(String[] args) throws Exception {
        String technicalDescriptor = "weightmanager";
        String processTempPath = new StringBuilder().append(System.getProperty("java.io.tmpdir")).append(technicalDescriptor).toString();
        String processParentPath = "C:\\_dev\\vhs\\git";
        String javaBasePackage = "com.dbr.weightmanager";
        DatabaseJPAUtil.DatabaseType databaseType = DatabaseJPAUtil.DatabaseType.MYSQL;
        String databaseHost = "85.235.67.10";
        int databasePort = 3306;
        String databaseUser = "root";
        String databasePSWD = "tgz014vb";
        ProcessModel model = new JavaDatabaseFullProcess(processTempPath, processParentPath, technicalDescriptor, javaBasePackage, databaseType, databaseHost, databasePort, databaseUser, databasePSWD, technicalDescriptor, "");
        ProcessGenerator.generate(model);
    }


    public JavaDatabaseFullProcess(String processTempPath, String processParentPath, String technicalDescriptor, String javaBasePackage, DatabaseJPAUtil.DatabaseType databaseType, String host, int port, String username, String password, String catalog, String schema) throws ClassNotFoundException, SQLException {
        super(processTempPath, processParentPath, technicalDescriptor);

        SpringBootProjectModel springBootProjectModel = new SpringBootProjectModel(this, "springboot", javaBasePackage);
        springBootProjectModel.setAddSpringBootSecurityModule(true);
        DatabaseJPAUtil databaseJPAUtil = new DatabaseJPAUtil(databaseType, host, port, username, password, catalog, schema);
        List<DatabaseJPAUtil.JPAEntityReference> jpaEntityReferences = databaseJPAUtil.getJPAEntityReferences(javaBasePackage);
        Template[] springBootItemBasicTemplates = {Template.ITEM_JAVA_DTO_TEMPLATE, Template.ITEM_JAVA_ENTITY_TEMPLATE, Template.ITEM_JAVA_CLAZZ_MAPPING_TEMPLATE, Template.ITEM_JAVA_SPRINGBOOT_JPA_REPOSITORY_TEMPLATE, Template.ITEM_JAVA_SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE, Template.ITEM_JAVA_SPRINGBOOT_JPA_SERVICE_SEARCH_TEMPLATE, Template.ITEM_JAVA_SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE, Template.ITEM_JAVA_SPRINGBOOT_REST_CONTROLLER_SEARCH_TEMPLATE};
        Collection<ItemModel> itemModels = new JPAEntityReference2ItemModelConverter().convert(springBootProjectModel, jpaEntityReferences, springBootItemBasicTemplates);
        springBootProjectModel.getItems().addAll(itemModels);
        getProjects().add(springBootProjectModel);


        NidocaProjectModel nidocaProjectModel = new NidocaProjectModel(this, "nidoca");
        Template[] nidocaItemTemplates = {Template.ITEM_TYPESCRIPT_MODEL_TEMPLATE, Template.ITEM_TYPESCRIPT_REMOTE_REPOSITORY, Template.ITEM_TYPESCRIPT_REMOTE_SERVICE, Template.ITEM_TYPESCRIPT_NIDOCA_COMPONENT_LIST};
        Collection<ItemModel> nidocaIemModels = new JPAEntityReference2ItemModelConverter().convert(nidocaProjectModel, jpaEntityReferences, nidocaItemTemplates);
        nidocaProjectModel.getItems().addAll(nidocaIemModels);
        getProjects().add(nidocaProjectModel);


    }


}
