package com.dbr.generator.springboot.app.rest;


import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.springboot.app.service.ItemBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "ITEM")
@RestController
@RequiredArgsConstructor
@RequestMapping(ItemRestBasicController.PATH_PREFIX)
public class ItemRestBasicController {

    public static final String PATH_PREFIX = "/ITEM";

    private final ItemBasicService service;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @CrossOrigin
    @ApiOperation(value = "get a list of all ItemDTO", response = ItemModel.class, responseContainer = "List")
    @GetMapping("ALL")
    public ResponseEntity<List<ItemModel>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @CrossOrigin
    @ApiOperation(value = "create a new object of ItemDTO and save it to database. return the saved object.", response = ItemModel.class, responseContainer = "ItemDTO")
    @PostMapping("CREATE")
    public ResponseEntity<ItemModel> create(@Valid @RequestBody ItemModel dto) {
        return ResponseEntity.ok(service.save(dto));
    }



}
