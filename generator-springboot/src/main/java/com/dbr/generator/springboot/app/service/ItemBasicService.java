package com.dbr.generator.springboot.app.service;

import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.entity.ItemEntity;
import com.dbr.generator.springboot.app.mapping.ItemEntityItemDTOMapping;
import com.dbr.generator.springboot.app.repository.ItemJPARepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 *
 * This service class provides basic functionality for the hibernate entity <code>ItemEntity</code>.
 * The return value of each function always returns a mapped <code>ItemDTO</code> object,
 * instead of the original hibernate entity.
 *
 */
@Service
@RequiredArgsConstructor
public class ItemBasicService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final EntityManager em;

    private final ItemJPARepository repository;

    private final ItemEntityItemDTOMapping mapping;

    /**
     * get a list of all available database <code>ItemEntity</code> object's, mapped to <code>ItemDTO</code> objects.
     *
     * @return a list of <code>ItemDTO's</code>
     *
     */
    public List<ItemModel> findAll() {
        log.debug("findAll");
        return mapping.toDTOs(repository.findAll());
    }

    /**
     * Find hibernate entity of type <code>ItemEntity</code> by id, mapped to <code>ItemDTO</code>.
     *
     * @param id of hibernate entity
     * @return optional object of type <code>Optional<ItemDTO></code>
     */
    public Optional<ItemModel> findById(Long id) {
        log.debug("findById: {}", id);
        Optional<ItemEntity> entityOptional = repository.findById(id);
        return entityOptional.isPresent() ? Optional.of(mapping.toDTO(entityOptional.get())) : Optional.ofNullable(null);
    }

    /**
     * save or update an <code>ItemEntity</code> object, mapped from <code>ItemDTO</code> object.
     *
     * @param dto to save or update
     * @return new saved or updated object as <code>ItemDTO</code>
     */
    public ItemModel save(ItemModel dto) {
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