package com.dbr.generator.springboot.app.repository;

import com.dbr.generator.basic.entity.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessJPARepository extends JpaRepository<ProcessEntity, Long> {

}

