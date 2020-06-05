package com.dbr.generator.springboot.app.rest;

import com.dbr.generator.springboot.app.dto.PropertyDTO;
import com.dbr.generator.springboot.app.service.PropertySearchService;

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

    public static final String PATH_PREFIX = "/PROPERTY";

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



}
