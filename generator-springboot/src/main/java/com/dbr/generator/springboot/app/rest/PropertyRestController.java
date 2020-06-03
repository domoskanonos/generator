package com.dbr.generator.springboot.app.rest;


import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.springboot.app.service.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "PROPERTY")
@RestController
@RequiredArgsConstructor
@RequestMapping(PropertyRestController.PATH_PREFIX)
public class PropertyRestController {

    public static final String PATH_PREFIX = "/PROPERTY";

    private final PropertyService service;

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
