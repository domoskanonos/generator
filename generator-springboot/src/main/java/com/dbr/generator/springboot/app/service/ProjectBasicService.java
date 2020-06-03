package com.dbr.generator.springboot.app.service;

import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.entity.ProjectEntity;
import com.dbr.generator.springboot.app.mapping.ProjectEntityProjectDTOMapping;
import com.dbr.generator.springboot.app.repository.ProjectJPARepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 *
 * This service class provides basic functionality for the hibernate entity <code>ProjectEntity</code>.
 * The return value of each function always returns a mapped <code>ProjectDTO</code> object,
 * instead of the original hibernate entity.
 *
 */
@Service
@RequiredArgsConstructor
public class ProjectBasicService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final EntityManager em;

    private final ProjectJPARepository repository;

    private final ProjectEntityProjectDTOMapping mapping;

    /**
     * get a list of all available database <code>ProjectEntity</code> object's, mapped to <code>ProjectDTO</code> objects.
     *
     * @return a list of <code>ProjectDTO's</code>
     *
     */
    public List<ProjectDTO> findAll() {
        log.debug("findAll");
        return mapping.toDTOs(repository.findAll());
    }

    /**
     * Find hibernate entity of type <code>ProjectEntity</code> by id, mapped to <code>ProjectDTO</code>.
     *
     * @param id of hibernate entity
     * @return optional object of type <code>Optional<ProjectDTO></code>
     */
    public Optional<ProjectDTO> findById(Long id) {
        log.debug("findById: {}", id);
        Optional<ProjectEntity> entityOptional = repository.findById(id);
        return entityOptional.isPresent() ? Optional.of(mapping.toDTO(entityOptional.get())) : Optional.ofNullable(null);
    }

    /**
     * save or update an <code>ProjectEntity</code> object, mapped from <code>ProjectDTO</code> object.
     *
     * @param dto to save or update
     * @return new saved or updated object as <code>ProjectDTO</code>
     */
    public ProjectDTO save(ProjectDTO dto) {
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