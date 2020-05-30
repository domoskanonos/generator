package com.dbr.generator.springboot.system.auth.repository;

import com.dbr.generator.springboot.system.auth.entity.AuthUserPrivilege;
import com.dbr.generator.springboot.system.auth.enumeration.AuthPrivilegeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserPrivilegeRepository extends JpaRepository<AuthUserPrivilege, Long> {

    AuthUserPrivilege findByName(AuthPrivilegeEnum name);

}
