package com.dbr.generator.springboot.ws.system.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {

    @Autowired
    EntityManager em;


    @Transactional
    public <T> List<T> getAllAudits(Class<T> entityClazz, Long id) {

        AuditReader reader = AuditReaderFactory.get(em);

        AuditQuery query = reader.createQuery()
                .forRevisionsOfEntity(entityClazz, true, true);

        query.add(AuditEntity.id().eq(id));

        return query.getResultList();

    }

}
