package com.dbr.generator.springboot.app.rest;

import com.dbr.generator.springboot.app.dto.PropertyDTO;
import com.dbr.generator.springboot.app.service.PropertyBasicService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import io.swagger.annotations.*;

import javax.validation.*;
import java.util.*;

@Api(tags = "PROPERTY")
@RestController
@RequiredArgsConstructor
@RequestMapping(PropertyRestBasicController.PATH_PREFIX)
public class PropertyRestBasicController {

    public static final String PATH_PREFIX = "/PROPERTY/BASIC";

    private final PropertyBasicService service;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @CrossOrigin
    @ApiOperation(value = "get a list of all PropertyDTO", response = PropertyDTO.class, responseContainer = "List")
    @GetMapping("ALL")
    public ResponseEntity<List<PropertyDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @CrossOrigin
    @ApiOperation(value = "create a new object of PropertyDTO and save it to database. return the saved object.", response = PropertyDTO.class, responseContainer = "PropertyDTO")
    @PostMapping("CREATE")
    public ResponseEntity<PropertyDTO> create(@Valid @RequestBody PropertyDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }


    @CrossOrigin
    @ApiOperation(value = "search object by id, method return BadRequest if object not exist, otherwise return the object of type PropertyDTO.", response = PropertyDTO.class, responseContainer = "PropertyDTO")
    @GetMapping("FIND_BY_ID/{id}")
    public ResponseEntity<PropertyDTO> findById(@PathVariable Long id) {
        Optional<PropertyDTO> dtoOptional = service.findById(id);
        if (dtoOptional.isPresent()) {
            return ResponseEntity.ok(dtoOptional.get());
        } else {
            log.error("object not found, id= {}", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @ApiOperation(value = "update an existing object with new values, object is from type PropertyDTO. if object not found, BadRequest will be returned.", response = PropertyDTO.class, responseContainer = "PropertyDTO")
    @PutMapping("UPDATE/{id}")
    public ResponseEntity<PropertyDTO> update(@PathVariable Long id, @Valid @RequestBody PropertyDTO dto) {
        if (!service.findById(id).isPresent()) {
            log.error("object not updated, id not found, id= {}, please check id", id);
            return ResponseEntity.badRequest().build();
        }

        dto.setId(id);
        return ResponseEntity.ok(service.save(dto));
    }

    @CrossOrigin
    @ApiOperation(value = "delete a object of type PropertyDTO by identifier (id). if object not found, BadRequest will be returned.", response = PropertyDTO.class, responseContainer = "PropertyDTO")
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
