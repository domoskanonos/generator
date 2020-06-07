package com.dbr.generator.springboot.app.rest;

import com.dbr.generator.basic.generator.process.ProcessGenerator;
import com.dbr.generator.springboot.app.dto.ProcessDTO;
import com.dbr.generator.springboot.app.mapping.ProcessDTOProcessModelMapping;
import com.dbr.generator.springboot.app.service.ProcessBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "GENERATOR")
@RestController
@RequiredArgsConstructor
@RequestMapping(GeneratorRestController.PATH_PREFIX)
public class GeneratorRestController {

    public static final String PATH_PREFIX = "/GENERATOR";

    private final ProcessBasicService processBasicService;

    private final ProcessDTOProcessModelMapping processDTOProcessModelMapping;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @CrossOrigin
    @ApiOperation(value = "generate process")
    @GetMapping("GENERATE_PROCESS_BY_ID/{id}")
    public ResponseEntity findById(@PathVariable Long id) throws Exception {
        Optional<ProcessDTO> dtoOptional = processBasicService.findById(id);
        if (dtoOptional.isPresent()) {
            ProcessGenerator.generate(processDTOProcessModelMapping.toModel(dtoOptional.get()));
            return ResponseEntity.ok().build();
        } else {
            log.error("object not found, id= {}", id);
            return ResponseEntity.badRequest().build();
        }
    }

}
