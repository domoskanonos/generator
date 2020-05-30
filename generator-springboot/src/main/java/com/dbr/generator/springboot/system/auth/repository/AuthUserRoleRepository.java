package com.dbr.generator.springboot.system.auth.repository;

import com.dbr.generator.springboot.system.auth.enumeration.AuthRoleEnum;
import com.dbr.generator.springboot.system.auth.entity.AuthUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRoleRepository extends JpaRepository<AuthUserRole, Integer> {

    AuthUserRole findByName(AuthRoleEnum roleEnum);

}
