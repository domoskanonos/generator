package com.dbr.generator.springboot.app.rest;

import com.dbr.generator.springboot.app.dto.PropertyDTO;
import com.dbr.generator.springboot.app.service.PropertySearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "${prefixPath}")
@RestController
@RequiredArgsConstructor
@RequestMapping(PropertyRestSearchController.PATH_PREFIX)
public class PropertyRestSearchController {

public static final String PATH_PREFIX = "/PROPERTY";

    private final PropertySearchService service;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @CrossOrigin
    @ApiOperation(value = "Search by different fields. Paging and sorting is possible. Result is a object of type Page<PropertyDTO>", response = PropertyDTO.class, responseContainer = "ResponseEntity<Page<PropertyDTO>>", notes = "<b>Property Info:</b><br/><br/><i>property page:</i> insert null, return page 0, insert negative page (f.e. -1) return 0<br/><br/><i>property size:</i> insert null, return size of 1, insert negative size (f.e. -1) return size of 1<br/><br/><i>property sort:</i> insert fieldName:direction pairs as string, f.e. fieldName=id and direction=desc is written as: 'id:desc;' . multiple field sorting is seperated with ';'")
    @GetMapping("ALL")
    public ResponseEntity<Page<PropertyDTO>> findByAllCriteriaQuery(
        @ApiParam(defaultValue = "0", example = "0") @RequestParam(required = false) Integer page,
        @ApiParam(defaultValue = "10", example = "10") @RequestParam(required = false) Integer size,
        @ApiParam(defaultValue = "", example = "id:desc;") @RequestParam(required = false) String sort){
        return ResponseEntity.ok(service.findByAllCriteriaQuery(page, size, sort));
    }

}
