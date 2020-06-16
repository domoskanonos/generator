package com.dbr.generator.springboot.app.repository;

import com.dbr.generator.basic.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemJPARepository extends JpaRepository<Item, Long> {

}

