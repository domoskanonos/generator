package com.dbr.generator.springboot.ws.system.general.jpa.entities.builder;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class PageBuilder<T, S> {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private Integer page;
    private Integer size;
    private String sort;

    public PageBuilder(Integer page, Integer size, String sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public Page<S> build(Class<T> clazz, EntityManager em) {
        log.debug("build page object, page: {}, size: {}, sort: {}", page, size, sort);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> entityRoot = cq.from(clazz);

        createPredicates(cb, cq, entityRoot);

        List<Sort.Order> sortsAsList = new ArrayList<>();
        if (StringUtils.isNotBlank(sort)) {
            String[] sortings = sort.split(";");
            for (String sorting : sortings) {
                String[] splittedSorting = sorting.split(":");
                String fieldName = splittedSorting[0];
                String direction = splittedSorting[1];
                switch (direction) {
                    case "asc":
                        sortsAsList.add(Sort.Order.asc(fieldName));
                        break;
                    case "desc":
                        sortsAsList.add(Sort.Order.desc(fieldName));
                        break;
                    default:
                        log.info("sorting ignored: {}", splittedSorting);
                }
            }
        }

        Sort sorts = Sort.by(sortsAsList);

        List<Order> order = sorts.get().map(sorting -> {
            String property = sorting.getProperty();
            if (sorting.isDescending()) {
                return cb.desc(entityRoot.get(property));
            } else {
                return cb.asc(entityRoot.get(property));
            }
        }).collect(Collectors.toList());

        cq.orderBy(order);

        TypedQuery<T> query = em.createQuery(cq);
        int totalRows = query.getResultList().size();
        if (size == null || size < 1) {
            size = totalRows > 1 ? totalRows : 1;
        }
        if (page == null || page < 0) {
            page = 0;
        }

        Pageable pageable = PageRequest.of(page, size, sorts);

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<S> s = toDTOs(query.getResultList());
        return new PageImpl<S>(s, pageable, totalRows);

    }

    protected abstract List<S> toDTOs(List<T> resultList);

    protected abstract void createPredicates(CriteriaBuilder cq, CriteriaQuery<T> productCriteriaQuery, Root<T> entityRoot);

}
