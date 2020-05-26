package com.dbr.generator.gen.common.csv.compound;

import com.dbr.generator.gen.CompoundAbstractGenerator;
import com.dbr.generator.gen.common.csv.CSV2ClazzGenerator;
import com.dbr.generator.gen.common.csv.CSVImporterGenerator;
import com.dbr.generator.gen.common.csv.CSVImporterTestGenerator;
import com.dbr.generator.gen.common.csv.compound.model.CSV2JavaCVM;
import com.dbr.generator.gen.common.csv.model.CSVClazzVM;
import com.dbr.generator.gen.common.csv.model.CSVImporterVM;
import com.dbr.generator.gen.common.csv.model.CSVJavaProperty;
import com.dbr.generator.gen.model.JavaProperty;
import com.dbr.generator.gen.server.entity.EntityGenerator;
import com.dbr.generator.gen.server.entity.model.EntityVM;
import com.dbr.util.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CSV2JavaCompoundGenerator extends CompoundAbstractGenerator {

    private CSV2JavaCVM cvm;

    public static void main(String[] args) throws Exception {
        CSV2JavaCVM csv2JavaCVM = new CSV2JavaCVM(new File("C:/en.openfoodfacts.org.products.csv"), "\t", "\n", true, 0, "Test", "com.dbr.generator.result", 1500);
        CSV2JavaCompoundGenerator csv2JavaCompoundGenerator = new CSV2JavaCompoundGenerator(csv2JavaCVM);
        csv2JavaCompoundGenerator.writeDown();
    }

    public CSV2JavaCompoundGenerator(CSV2JavaCVM cvm) {
        super(cvm.getBasePackageName());
        this.cvm = cvm;
    }

    @Override
    public void writeDown() throws Exception {

        String clazzSimpleBaseName = this.cvm.getClazzSimpleBaseName();
        String columnSplit = this.cvm.getColumnSplit();
        String rowSplit = this.cvm.getRowSplit();
        String content = this.cvm.getContent();
        Boolean withHeaderRow = this.cvm.isWithHeaderRow();

        String dtoClazzSimpleName = clazzSimpleBaseName + "DTO";
        String dtoClazzPackageName = basePackageName + ".dto";

        CSVClazzVM csvClazzVM = new CSVClazzVM(dtoClazzPackageName, dtoClazzSimpleName, content, columnSplit, rowSplit, withHeaderRow);
        if (this.cvm.isGenerateModel()) {
            log.info("generate model...");
            new CSV2ClazzGenerator(csvClazzVM).writeDown();
        }

        List<CSVJavaProperty> javaProperties = csvClazzVM.getProperties();
        List<EntityVM.EntityProperty> entityProperties = EntityVM.mapCSV(javaProperties);

        Integer idColumnIndex = this.cvm.getIdColumnIndex();
        if (idColumnIndex != null) {
            entityProperties.get(idColumnIndex).setPrimaryKey(true);
        } else {
            List<EntityVM.EntityProperty> newEntityProperties = new ArrayList<>();
            EntityVM.EntityProperty idEntityProperty = new EntityVM.EntityProperty(new JavaProperty("id", "Long"));
            idEntityProperty.setPrimaryKey(true);
            newEntityProperties.add(idEntityProperty);
            newEntityProperties.addAll(entityProperties);
        }

        String entityClazzPackageName = basePackageName + ".entity";
        EntityVM entityVM = new EntityVM(
                entityClazzPackageName,
                StringUtil.toDatabaseName(clazzSimpleBaseName),
                clazzSimpleBaseName,
                null,
                false,
                null,
                entityProperties);
        if (this.cvm.isGenerateJPAEntity()) {
            log.info("generate entity...");
            new EntityGenerator(entityVM).writeDown();
        }

        String importerClazzSimpleName = clazzSimpleBaseName + "CSVImporter";
        String csvPackageName = basePackageName + ".csv";
        CSVImporterVM csvImporterVM = new CSVImporterVM(csvPackageName, importerClazzSimpleName, dtoClazzPackageName, dtoClazzSimpleName, columnSplit, rowSplit, withHeaderRow, this.cvm.getFile(), this.cvm.getContent(), csvClazzVM.getRowSize(), javaProperties);
        CSVImporterGenerator csvImporterGenerator = new CSVImporterGenerator(csvImporterVM);

        if (this.cvm.isGenerateCSVImporter()) {
            log.info("generate csv importer...");
            csvImporterGenerator.writeDown();
        }

        CSVImporterTestGenerator csvImporterTestGenerator = new CSVImporterTestGenerator(csvImporterVM);
        if (this.cvm.isGenerateCSVImporterTest()) {
            log.info("generate test...");
            csvImporterTestGenerator.writeDown();
        }

    }

}
