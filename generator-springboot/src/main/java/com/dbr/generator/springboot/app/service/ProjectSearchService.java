package com.dbr.generator.springboot.app.service;

import com.dbr.generator.basic.entity.ProjectEntity;
import com.dbr.generator.springboot.app.dto.ProjectDTO;
import com.dbr.generator.springboot.app.mapping.ProjectEntityProjectDTOMapping;
import com.dbr.generator.springboot.system.jpa.entities.builder.PageBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * This service class provides basic functionality for the hibernate entity <code>ProjectEntity</code>.
 * The return value of each function always returns a mapped <code>ProjectDTO</code> object,
 * instead of the original hibernate entity.
 *
 */
@Service
@RequiredArgsConstructor
public class ProjectSearchService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final EntityManager em;

    private final ProjectEntityProjectDTOMapping mapping;

    /**
     * Returns a pagination object with a list of <code>ProjectDTO</code> objects restricted to the search criteria.
     * This list corresponds to the list of <code>ProjectEntity</code> entities from the database.
     * Result will be sorted according to the given sorting string.
     * Result is restricted according to the paging criteria passed (page, size).
     *
     * @param page current page of pagination
     * @param size current item size of pagination
     * @param sort current sorting string for sorting
     *      *
     * @return a <code>Page<ProjectDTO></code> object, containing a list of <code>ProjectDTO</code> objects and additional paging information.
     *
     */
    @Transactional
    public Page<ProjectDTO> findByAllCriteriaQuery(Integer page, Integer size, String sort, Long id, String technicalDescriptor, String javaBasePackage) {
        log.debug("findByAllCriteriaQuery, page: {}, size: {}, sort: {}, values: {} {} {} ", page, size, sort );
        return new PageBuilder<ProjectEntity, ProjectDTO>(page, size, sort) {

            @Override
            protected List<ProjectDTO> toDTOs(List<ProjectEntity> resultList) {
                return mapping.toDTOs(resultList);
            }

            @Override
            protected void createPredicates(CriteriaBuilder cb, CriteriaQuery<ProjectEntity> cq, Root<ProjectEntity> entityRoot) {
                List<Predicate> predicates = new ArrayList<>();
                if (predicates.size() > 0) {
                    cq.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
                }
            }
        }.build(ProjectEntity.class, em);
    }
}
