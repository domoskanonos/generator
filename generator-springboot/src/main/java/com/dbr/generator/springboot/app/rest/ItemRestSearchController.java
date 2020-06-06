package com.dbr.generator.springboot.app.rest;

import com.dbr.generator.springboot.app.dto.ItemDTO;
import com.dbr.generator.springboot.app.service.ItemSearchService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import io.swagger.annotations.*;

import javax.validation.Valid;
import java.util.*;

@Api(tags = "ITEM")
@RestController
@RequiredArgsConstructor
@RequestMapping(ItemRestSearchController.PATH_PREFIX)
public class ItemRestSearchController {

public static final String PATH_PREFIX = "/ITEM/SEARCH";

    private final ItemSearchService service;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @CrossOrigin
    @ApiOperation(value = "Search by different fields. Paging and sorting is possible. Result is a object of type Page<ItemDTO>", response = ItemDTO.class, responseContainer = "ResponseEntity<Page<ItemDTO>>", notes = "<b>Property Info:</b><br/><br/><i>property page:</i> insert null, return page 0, insert negative page (f.e. -1) return 0<br/><br/><i>property size:</i> insert null, return size of 1, insert negative size (f.e. -1) return size of 1<br/><br/><i>property sort:</i> insert fieldName:direction pairs as string, f.e. fieldName=id and direction=desc is written as: 'id:desc;' . multiple field sorting is seperated with ';'")
    @GetMapping("ALL")
    public ResponseEntity<Page<ItemDTO>> findByAllCriteriaQuery(
        @ApiParam(defaultValue = "0", example = "0") @RequestParam(required = false) Integer page,
        @ApiParam(defaultValue = "10", example = "10") @RequestParam(required = false) Integer size,
        @ApiParam(defaultValue = "", example = "id:desc;") @RequestParam(required = false) String sort, Long id, String name){
        return ResponseEntity.ok(service.findByAllCriteriaQuery(page, size, sort, id, name));
    }

}
