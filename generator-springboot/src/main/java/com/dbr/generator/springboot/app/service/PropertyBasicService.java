package com.dbr.generator.springboot.app.service;

import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.entity.PropertyEntity;
import com.dbr.generator.springboot.app.mapping.PropertyEntityPropertyDTOMapping;
import com.dbr.generator.springboot.app.repository.PropertyJPARepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 *
 * This service class provides basic functionality for the hibernate entity <code>PropertyEntity</code>.
 * The return value of each function always returns a mapped <code>PropertyDTO</code> object,
 * instead of the original hibernate entity.
 *
 */
@Service
@RequiredArgsConstructor
public class PropertyBasicService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final EntityManager em;

    private final PropertyJPARepository repository;

    private final PropertyEntityPropertyDTOMapping mapping;

    /**
     * get a list of all available database <code>PropertyEntity</code> object's, mapped to <code>PropertyDTO</code> objects.
     *
     * @return a list of <code>PropertyDTO's</code>
     *
     */
    public List<PropertyDTO> findAll() {
        log.debug("findAll");
        return mapping.toDTOs(repository.findAll());
    }

    /**
     * Find hibernate entity of type <code>PropertyEntity</code> by id, mapped to <code>PropertyDTO</code>.
     *
     * @param id of hibernate entity
     * @return optional object of type <code>Optional<PropertyDTO></code>
     */
    public Optional<PropertyDTO> findById(Long id) {
        log.debug("findById: {}", id);
        Optional<PropertyEntity> entityOptional = repository.findById(id);
        return entityOptional.isPresent() ? Optional.of(mapping.toDTO(entityOptional.get())) : Optional.ofNullable(null);
    }

    /**
     * save or update an <code>PropertyEntity</code> object, mapped from <code>PropertyDTO</code> object.
     *
     * @param dto to save or update
     * @return new saved or updated object as <code>PropertyDTO</code>
     */
    public PropertyDTO save(PropertyDTO dto) {
        log.debug("save or update object: {}", dto);
        return mapping.toDTO(repository.save(mapping.toEntity(dto)));
    }

    /**
     * delete hibernate entity by id.
     *
     * @param id
     */
    public void deleteById(Long id) {
        log.debug("delete by id: {}", id);
        repository.deleteById(id);
    }

}