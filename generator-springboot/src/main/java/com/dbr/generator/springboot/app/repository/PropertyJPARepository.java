package com.dbr.generator.springboot.app.repository;

import com.dbr.generator.basic.entity.Property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyJPARepository extends JpaRepository<Property, Long> {

}

