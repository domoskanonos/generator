package com.dbr.generator.springboot.app.repository;

import com.dbr.generator.basic.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectJPARepository extends JpaRepository<ProjectEntity, Long> {

}

