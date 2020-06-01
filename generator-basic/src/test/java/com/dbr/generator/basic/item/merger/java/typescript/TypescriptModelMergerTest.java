package com.dbr.generator.basic.item.merger.java.typescript;

import com.dbr.generator.basic.BasicTestUtil;
import com.dbr.generator.basic.item.merger.ItemMerger;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class TypescriptModelMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        ItemMerger itemMerger = new ItemMerger(BasicTestUtil.subitemDTO1);
        String content = itemMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}