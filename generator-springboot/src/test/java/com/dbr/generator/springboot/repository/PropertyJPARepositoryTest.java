package com.dbr.generator.springboot.repository;

import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.entity.Item;
import com.dbr.generator.basic.merger.ItemTemplates;
import com.dbr.generator.springboot.app.mapping.ItemItemDTOMapping;
import com.dbr.generator.springboot.app.repository.ItemJPARepository;
import com.dbr.generator.springboot.app.repository.PropertyJPARepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PropertyJPARepositoryTest {

    @Autowired
    private PropertyJPARepository propertyJPARepository;

    @Autowired
    private ItemJPARepository itemJPARepository;

    @Autowired
    ItemItemDTOMapping itemItemDTOMapping;

    @Test
    public void pdfApplicationProperties() {
        ItemDTO itemDTO = new JavaClass2ItemDTOConverter().convert("", ItemTemplates.DTO_TEMPLATE, ItemDTO.class);
        Item item = itemItemDTOMapping.toEntity(itemDTO);
        item = itemJPARepository.save(item);
        assertThat(item.getId()).isNotNull();
    }

}