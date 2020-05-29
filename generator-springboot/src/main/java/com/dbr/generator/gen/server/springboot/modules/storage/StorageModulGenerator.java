package com.dbr.generator.gen.server.springboot.modules.storage;

import com.dbr.generator.gen.server.springboot.modules.AbstractModuleGenerator;
import com.dbr.generator.gen.server.springboot.modules.model[0].ModulItem;

import java.util.ArrayList;
import java.util.List;

public class StorageModulGenerator extends AbstractModuleGenerator {

    public StorageModulGenerator(String packageName) {
        super(packageName);
    }

    @Override
    public void generate() throws Exception {
        List<ModulItem> items = new ArrayList<>();
        items.add(new ModulItem(".exception", "storage/storage-exception.vm", "StorageException.java"));
        items.add(new ModulItem(".service", "storage/storage-service.vm", "StorageService.java"));
        items.add(new ModulItem(".rest", "storage/storage-rest-controller.vm", "StorageRestController.java"));
        this.generate(items);
    }

}
