package com.dbr.generator.springboot.app.rest;

import com.dbr.generator.springboot.app.dto.ProcessDTO;
import com.dbr.generator.springboot.app.service.ProcessSearchService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import io.swagger.annotations.*;

import javax.validation.*;
import java.util.*;

@Api(tags = "PROCESS")
@RestController
@RequiredArgsConstructor
@RequestMapping(ProcessRestBasicController.PATH_PREFIX)
public class ProcessRestBasicController {

    public static final String PATH_PREFIX = "/PROCESS";

    private final ProcessBasicService service;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @CrossOrigin
    @ApiOperation(value = "get a list of all ProcessDTO", response = ProcessDTO.class, responseContainer = "List")
    @GetMapping("ALL")
    public ResponseEntity<List<ProcessDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @CrossOrigin
    @ApiOperation(value = "create a new object of ProcessDTO and save it to database. return the saved object.", response = ProcessDTO.class, responseContainer = "ProcessDTO")
    @PostMapping("CREATE")
    public ResponseEntity<ProcessDTO> create(@Valid @RequestBody ProcessDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }



}
