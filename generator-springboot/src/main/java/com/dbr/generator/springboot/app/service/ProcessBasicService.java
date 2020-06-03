package com.dbr.generator.springboot.app.service;

import com.dbr.generator.basic.dto.ProcessDTO;
import com.dbr.generator.basic.entity.ProcessEntity;
import com.dbr.generator.springboot.app.mapping.ProcessEntityProcessDTOMapping;
import com.dbr.generator.springboot.app.repository.ProcessJPARepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 *
 * This service class provides basic functionality for the hibernate entity <code>ProcessEntity</code>.
 * The return value of each function always returns a mapped <code>ProcessDTO</code> object,
 * instead of the original hibernate entity.
 *
 */
@Service
@RequiredArgsConstructor
public class ProcessBasicService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final EntityManager em;

    private final ProcessJPARepository repository;

    private final ProcessEntityProcessDTOMapping mapping;

    /**
     * get a list of all available database <code>ProcessEntity</code> object's, mapped to <code>ProcessDTO</code> objects.
     *
     * @return a list of <code>ProcessDTO's</code>
     *
     */
    public List<ProcessDTO> findAll() {
        log.debug("findAll");
        return mapping.toDTOs(repository.findAll());
    }

    /**
     * Find hibernate entity of type <code>ProcessEntity</code> by id, mapped to <code>ProcessDTO</code>.
     *
     * @param id of hibernate entity
     * @return optional object of type <code>Optional<ProcessDTO></code>
     */
    public Optional<ProcessDTO> findById(Long id) {
        log.debug("findById: {}", id);
        Optional<ProcessEntity> entityOptional = repository.findById(id);
        return entityOptional.isPresent() ? Optional.of(mapping.toDTO(entityOptional.get())) : Optional.ofNullable(null);
    }

    /**
     * save or update an <code>ProcessEntity</code> object, mapped from <code>ProcessDTO</code> object.
     *
     * @param dto to save or update
     * @return new saved or updated object as <code>ProcessDTO</code>
     */
    public ProcessDTO save(ProcessDTO dto) {
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