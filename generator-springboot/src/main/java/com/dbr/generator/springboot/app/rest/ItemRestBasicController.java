package com.dbr.generator.springboot.app.rest;

import com.dbr.generator.springboot.app.dto.ItemDTO;
import com.dbr.generator.springboot.app.service.ItemBasicService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import io.swagger.annotations.*;

import javax.validation.*;
import java.util.*;

@Api(tags = "ITEM")
@RestController
@RequiredArgsConstructor
@RequestMapping(ItemRestBasicController.PATH_PREFIX)
public class ItemRestBasicController {

    public static final String PATH_PREFIX = "/ITEM/BASIC";

    private final ItemBasicService service;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @CrossOrigin
    @ApiOperation(value = "get a list of all ItemDTO", response = ItemDTO.class, responseContainer = "List")
    @GetMapping("ALL")
    public ResponseEntity<List<ItemDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @CrossOrigin
    @ApiOperation(value = "create a new object of ItemDTO and save it to database. return the saved object.", response = ItemDTO.class, responseContainer = "ItemDTO")
    @PostMapping("CREATE")
    public ResponseEntity<ItemDTO> create(@Valid @RequestBody ItemDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }


    @CrossOrigin
    @ApiOperation(value = "search object by id, method return BadRequest if object not exist, otherwise return the object of type ItemDTO.", response = ItemDTO.class, responseContainer = "ItemDTO")
    @GetMapping("FIND_BY_ID/{id}")
    public ResponseEntity<ItemDTO> findById(@PathVariable Long id) {
        Optional<ItemDTO> dtoOptional = service.findById(id);
        if (dtoOptional.isPresent()) {
            return ResponseEntity.ok(dtoOptional.get());
        } else {
            log.error("object not found, id= {}", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @ApiOperation(value = "update an existing object with new values, object is from type ItemDTO. if object not found, BadRequest will be returned.", response = ItemDTO.class, responseContainer = "ItemDTO")
    @PutMapping("UPDATE/{id}")
    public ResponseEntity<ItemDTO> update(@PathVariable Long id, @Valid @RequestBody ItemDTO dto) {
        if (!service.findById(id).isPresent()) {
            log.error("object not updated, id not found, id= {}, please check id", id);
            return ResponseEntity.badRequest().build();
        }

        dto.setId(id);
        return ResponseEntity.ok(service.save(dto));
    }

    @CrossOrigin
    @ApiOperation(value = "delete a object of type ItemDTO by identifier (id). if object not found, BadRequest will be returned.", response = ItemDTO.class, responseContainer = "ItemDTO")
    @DeleteMapping("DELETE/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            log.error("object not deleted, id not found, id= {}, please check id", id);
            ResponseEntity.badRequest().build();
        }

        service.deleteById(id);

        return ResponseEntity.ok().build();
    }




}
