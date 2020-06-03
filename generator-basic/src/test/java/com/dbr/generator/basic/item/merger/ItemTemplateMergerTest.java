package com.dbr.generator.basic.item.merger;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.merger.ItemTemplateMerger;
import com.dbr.generator.basic.merger.TemplateEnum;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.dbr.generator.GeneratorProjectMetaData.SPRING_BOOT_JAVA_PROJECT_DTO;
import static org.junit.Assert.assertNotNull;

public class ItemTemplateMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void mergeAllTemplates() throws IOException {
        ItemDTO item = new ItemDTO("Item", TypeEnum.TYPE_LONG, SPRING_BOOT_JAVA_PROJECT_DTO, TemplateEnum.values());

        for (TemplateEnum templateEnum : item.getTemplate()) {
            String content = new ItemTemplateMerger().create(templateEnum, item);
            log.info(content);
            assertNotNull(content);
            //assertFalse(content.contains("${"));
        }

    }


}