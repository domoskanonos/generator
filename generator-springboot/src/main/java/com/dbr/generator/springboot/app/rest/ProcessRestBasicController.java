package com.dbr.generator.springboot.app.rest;

import com.dbr.generator.springboot.app.dto.ProcessDTO;
import com.dbr.generator.springboot.app.service.ProcessBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = "PROCESS")
@RestController
@RequiredArgsConstructor
@RequestMapping(ProcessRestBasicController.PATH_PREFIX)
public class ProcessRestBasicController {

    public static final String PATH_PREFIX = "/PROCESS/BASIC";

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


    @CrossOrigin
    @ApiOperation(value = "search object by id, method return BadRequest if object not exist, otherwise return the object of type ProcessDTO.", response = ProcessDTO.class, responseContainer = "ProcessDTO")
    @GetMapping("FIND_BY_ID/{id}")
    public ResponseEntity<ProcessDTO> findById(@PathVariable java.lang.Long id) {
        Optional<ProcessDTO> dtoOptional = service.findById(id);
        if (dtoOptional.isPresent()) {
            return ResponseEntity.ok(dtoOptional.get());
        } else {
            log.error("object not found, id= {}", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @ApiOperation(value = "update an existing object with new values, object is from type ProcessDTO. if object not found, BadRequest will be returned.", response = ProcessDTO.class, responseContainer = "ProcessDTO")
    @PutMapping("UPDATE/{id}")
    public ResponseEntity<ProcessDTO> update(@PathVariable java.lang.Long id, @Valid @RequestBody ProcessDTO dto) {
        if (!service.findById(id).isPresent()) {
            log.error("object not updated, id not found, id= {}, please check id", id);
            return ResponseEntity.badRequest().build();
        }

        dto.setId(id);
        return ResponseEntity.ok(service.save(dto));
    }

    @CrossOrigin
    @ApiOperation(value = "delete a object of type ProcessDTO by identifier (id). if object not found, BadRequest will be returned.", response = ProcessDTO.class, responseContainer = "ProcessDTO")
    @DeleteMapping("DELETE/{id}")
    public ResponseEntity delete(@PathVariable java.lang.Long id) {
        if (!service.findById(id).isPresent()) {
            log.error("object not deleted, id not found, id= {}, please check id", id);
            ResponseEntity.badRequest().build();
        }

        service.deleteById(id);

        return ResponseEntity.ok().build();
    }




}
