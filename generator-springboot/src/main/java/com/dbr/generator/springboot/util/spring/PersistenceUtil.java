package com.dbr.generator.springboot.util.spring;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersistenceUtil {

    public Pageable preparePageable(CriteriaBuilder cb, CriteriaQuery<?> cq, Root<?> entityRoot, TypedQuery<?> query,
            Integer page, Integer size, String sort, int totalRows) {
        List<Sort.Order> sorts = new ArrayList<>();
        if (!sort.isBlank()) {
            String[] sortings = sort.split(";");
            for (String sorting : sortings) {
                String[] splittedSorting = sorting.split(":");
                String fieldName = splittedSorting[0];
                String direction = splittedSorting[1];
                switch (direction) {
                case "asc":
                    sorts.add(Sort.Order.asc(fieldName));
                    break;
                case "desc":
                    sorts.add(Sort.Order.desc(fieldName));
                    break;
                }
            }
        }
        if (size == null || size == 0 || size == -1) {
            size = totalRows;
        }
        if (page == null || page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(sorts));

        List<Order> order = pageable.getSort().get().map(sorting -> {
            String property = sorting.getProperty();
            if (sorting.isDescending()) {
                return cb.desc(entityRoot.get(property));
            } else {
                return cb.asc(entityRoot.get(property));
            }
        }).collect(Collectors.toList());

        cq.orderBy(order);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return pageable;
    }

}
