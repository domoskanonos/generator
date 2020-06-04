package com.dbr.generator.springboot.app.rest;

import com.dbr.generator.basic.model.project.ProjectModel;
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

@Api(tags = "PROJECT")
@RestController
@RequiredArgsConstructor
@RequestMapping(ProjectRestBasicController.PATH_PREFIX)
public class ProjectRestBasicController {

    public static final String PATH_PREFIX = "/PROJECT";

    private final ProjectBasicService service;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @CrossOrigin
    @ApiOperation(value = "get a list of all ProjectDTO", response = ProjectModel.class, responseContainer = "List")
    @GetMapping("ALL")
    public ResponseEntity<List<ProjectModel>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @CrossOrigin
    @ApiOperation(value = "create a new object of ProjectDTO and save it to database. return the saved object.", response = ProjectModel.class, responseContainer = "ProjectDTO")
    @PostMapping("CREATE")
    public ResponseEntity<ProjectModel> create(@Valid @RequestBody ProjectModel dto) {
        return ResponseEntity.ok(service.save(dto));
    }



}