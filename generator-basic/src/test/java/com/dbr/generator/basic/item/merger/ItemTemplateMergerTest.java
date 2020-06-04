package com.dbr.generator.basic.item.merger;

import com.dbr.generator.GeneratorProjectMetaData;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.enumeration.ItemType;
import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.merger.ItemTemplateMerger;
import com.dbr.generator.basic.merger.TemplateEnum;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ItemTemplateMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void mergeAllTemplates() throws IOException {
        ItemModel item = new ItemModel(GeneratorProjectMetaData.SPRING_BOOT_JAVA_PROJECT_DTO, "Item", ItemType.JAVA, TypeEnum.TYPE_LONG, TemplateEnum.values());

        for (TemplateEnum templateEnum : item.getTemplate()) {
            String content = new ItemTemplateMerger().create(templateEnum, item);
            log.info(content);
            assertNotNull(content);
            //assertFalse(content.contains("${"));
        }

    }


}