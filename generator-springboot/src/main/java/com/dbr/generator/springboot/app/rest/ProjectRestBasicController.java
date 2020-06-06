package com.dbr.generator.springboot.app.rest;

import com.dbr.generator.springboot.app.dto.ProjectDTO;
import com.dbr.generator.springboot.app.service.ProjectBasicService;
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

@Api(tags = "PROJECT")
@RestController
@RequiredArgsConstructor
@RequestMapping(ProjectRestBasicController.PATH_PREFIX)
public class ProjectRestBasicController {

    public static final String PATH_PREFIX = "/PROJECT/BASIC";

    private final ProjectBasicService service;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @CrossOrigin
    @ApiOperation(value = "get a list of all ProjectDTO", response = ProjectDTO.class, responseContainer = "List")
    @GetMapping("ALL")
    public ResponseEntity<List<ProjectDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @CrossOrigin
    @ApiOperation(value = "create a new object of ProjectDTO and save it to database. return the saved object.", response = ProjectDTO.class, responseContainer = "ProjectDTO")
    @PostMapping("CREATE")
    public ResponseEntity<ProjectDTO> create(@Valid @RequestBody ProjectDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }


    @CrossOrigin
    @ApiOperation(value = "search object by id, method return BadRequest if object not exist, otherwise return the object of type ProjectDTO.", response = ProjectDTO.class, responseContainer = "ProjectDTO")
    @GetMapping("FIND_BY_ID/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable java.lang.Long id) {
        Optional<ProjectDTO> dtoOptional = service.findById(id);
        if (dtoOptional.isPresent()) {
            return ResponseEntity.ok(dtoOptional.get());
        } else {
            log.error("object not found, id= {}", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @ApiOperation(value = "update an existing object with new values, object is from type ProjectDTO. if object not found, BadRequest will be returned.", response = ProjectDTO.class, responseContainer = "ProjectDTO")
    @PutMapping("UPDATE/{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable java.lang.Long id, @Valid @RequestBody ProjectDTO dto) {
        if (!service.findById(id).isPresent()) {
            log.error("object not updated, id not found, id= {}, please check id", id);
            return ResponseEntity.badRequest().build();
        }

        dto.setId(id);
        return ResponseEntity.ok(service.save(dto));
    }

    @CrossOrigin
    @ApiOperation(value = "delete a object of type ProjectDTO by identifier (id). if object not found, BadRequest will be returned.", response = ProjectDTO.class, responseContainer = "ProjectDTO")
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
