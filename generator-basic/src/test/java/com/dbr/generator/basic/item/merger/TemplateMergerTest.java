package com.dbr.generator.basic.item.merger;

import com.dbr.generator.GeneratorProjectMetaData;
import com.dbr.generator.basic.enumeration.PropertyTypeEnum;
import com.dbr.generator.basic.merger.TemplateMerger;
import com.dbr.generator.basic.enumeration.TemplateEnum;
import com.dbr.generator.basic.model.ItemModel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class TemplateMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void mergeAllTemplates() throws IOException {
        ItemModel item = new ItemModel(GeneratorProjectMetaData.SPRING_BOOT_JAVA_PROJECT_MODEL, "Item", PropertyTypeEnum.LONG, TemplateEnum.values());

        for (TemplateEnum templateEnum : item.getTemplate()) {
            String content = new TemplateMerger().create(templateEnum, item);
            log.info(content);
            assertNotNull(content);
            //assertFalse(content.contains("${"));
        }

    }


}