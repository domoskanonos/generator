package com.dbr.generator.springboot.system.listener;

import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.ProcessDTO;
import com.dbr.generator.basic.merger.TemplateEnum;
import com.dbr.generator.basic.entity.Item;
import com.dbr.generator.springboot.app.mapping.ItemItemDTOMapping;
import com.dbr.generator.springboot.app.repository.ItemJPARepository;
import com.dbr.generator.springboot.app.repository.PropertyJPARepository;
import com.dbr.generator.springboot.system.ApplicationProperties;
import com.dbr.generator.springboot.system.enumeration.BuildEnvironment;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final ApplicationProperties applicationProperties;
    private final PropertyJPARepository propertyJPARepository;
    private final ItemJPARepository itemJPARepository;

    final
    ItemItemDTOMapping itemItemDTOMapping;

    public InitialDataLoader(ApplicationProperties applicationProperties, PropertyJPARepository propertyJPARepository, ItemJPARepository itemJPARepository, ItemItemDTOMapping itemItemDTOMapping) {
        this.applicationProperties = applicationProperties;
        this.propertyJPARepository = propertyJPARepository;
        this.itemJPARepository = itemJPARepository;
        this.itemItemDTOMapping = itemItemDTOMapping;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (applicationProperties.getBuildEnvironments().contains(BuildEnvironment.DEV)) {


            ItemDTO itemDTO = new JavaClass2ItemDTOConverter().convert(TemplateEnum.DTO_TEMPLATE, "_dev/vhs/git/generator/generator-springboot", ProcessDTO.class);
            Item item = itemItemDTOMapping.toEntity(itemDTO);
            item = itemJPARepository.save(item);

        }

    }

}
