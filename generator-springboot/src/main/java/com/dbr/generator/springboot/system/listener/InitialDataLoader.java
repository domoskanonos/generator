package com.dbr.generator.springboot.system.listener;

import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.ProcessDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.entity.Item;
import com.dbr.generator.basic.merger.ItemTemplates;
import com.dbr.generator.springboot.app.mapping.ItemItemDTOMapping;
import com.dbr.generator.springboot.app.repository.ItemJPARepository;
import com.dbr.generator.springboot.app.repository.PropertyJPARepository;
import com.dbr.generator.springboot.system.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private PropertyJPARepository propertyJPARepository;

    @Autowired
    private ItemJPARepository itemJPARepository;

    @Autowired
    ItemItemDTOMapping itemItemDTOMapping;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ItemDTO itemDTO = new JavaClass2ItemDTOConverter().convert("_dev/vhs/git/generator/generator-springboot", ItemTemplates.DTO_TEMPLATE, ProcessDTO.class);
        Item item = itemItemDTOMapping.toEntity(itemDTO);
        item = itemJPARepository.save(item);


    }

}
